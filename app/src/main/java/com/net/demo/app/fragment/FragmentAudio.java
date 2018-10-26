package com.net.demo.app.fragment;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;
import com.net.demo.app.R;
import net.iwebrtc.audioprocess.sdk.AudioProcess;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/9/4.
 */

public class FragmentAudio extends Fragment implements View.OnClickListener {


    public static String TAG = "DemoActivity";

    public SeekBar skbVolume;//调节音量
    public boolean isProcessing = true;//是否录放的标记
    public boolean isRecording = false;//是否录放的标记

    public static final int frequency = 16000;
    //ChannelConfig:录制通道，可以为AudioFormat.CHANNEL_CONFIGURATION_MONO
    //和AudioFormat.CHANNEL_CONFIGURATION_STEREO
    public static final int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    public static final int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;

    public int recBufSize, playBufSize;
    public AudioRecord audioRecord;
    public AudioTrack audioTrack;
    AudioProcess mAudioProcess;

    private Button btnWavTest;
//    private TextView tv;

    public static FragmentAudio newInstance(String name) {

        Bundle args = new Bundle();
        args.putString("name", name);

        FragmentAudio fragment = new FragmentAudio();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recBufSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration, audioEncoding);
        Log.d(TAG, "recBufSize:" + recBufSize);
        playBufSize = AudioTrack.getMinBufferSize(frequency, channelConfiguration, audioEncoding);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, frequency, channelConfiguration, audioEncoding, recBufSize);
        audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, frequency, channelConfiguration, audioEncoding, playBufSize, AudioTrack.MODE_STREAM);

        view.findViewById(R.id.btnRecord).setOnClickListener(this);
        view.findViewById(R.id.btnStop).setOnClickListener(this);
        view.findViewById(R.id.btnWavTest).setOnClickListener(this);
        view.findViewById(R.id.btnJZTest).setOnClickListener(this);

        skbVolume = (SeekBar) view.findViewById(R.id.skbVolume);
        skbVolume.setMax(100);//音量调节的极限
        skbVolume.setProgress(50);//设置seekbar的位置值
        audioTrack.setStereoVolume(0.7f, 0.7f);//设置当前音量大小
        skbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                float vol = (float) (seekBar.getProgress()) / (float) (seekBar.getMax());
                audioTrack.setStereoVolume(vol, vol);//设置音量
                Log.d(TAG, "onStopTrackingTouch:" + vol);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }
        });

        ((CheckBox)view.findViewById(R.id.cb_ap)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton view, boolean checked) {
                isProcessing = checked;
                Log.d(TAG, "isProcessing:" + isProcessing);
            }
        });
        mAudioProcess = new AudioProcess();
        mAudioProcess.init(frequency, 2, 1); //-------16000  2个字节  1个声道

//        tv = (TextView) view.findViewById(R.id.fragment_test_tv);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.get("name").toString();
            Log.d(TAG, "name:" + name);
//            tv.setText(name);
        }
        InitSound();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRecord) {
            if(!isRecording) {
                isRecording = true;
                Log.d(TAG, "isRecording:" + isRecording);
                new RecordPlayThread().start();
            }
        } else if (v.getId() == R.id.btnStop) {
            isRecording = false;
            Log.d(TAG, "isRecording:" + isRecording);
        }
        else if (v.getId() == R.id.btnWavTest)
        {
            //播放声音
            if(isLoaded==true)
            {
                mPlayIndex++;
                playSound(mPlayIndex, 0);
                if(mPlayIndex >= 5){
                    mPlayIndex = 0;
                }
                Log.d(TAG, "mPlayIndex:" + mPlayIndex);
            }

        }
        else if (v.getId() == R.id.btnJZTest) {
            if(!isRecording) {
                isRecording = true;
                Log.d(TAG, "btnJZTest isRecording:" + isRecording);
                new JZRecordPlayThread().start();
//                new JZRecordPlayThreadPlaySound().start();

            }
        }
    }

    SoundPool sp;
    HashMap<Integer, Integer> sounddata;
    Context mcontext;
    Boolean isLoaded;
    int  mPlayIndex=0;
    //初始化声音
    public void InitSound() {
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sounddata = new HashMap<Integer, Integer>();
        sounddata.put(1, sp.load(getActivity(), R.raw.m0000, 1));
        sounddata.put(2, sp.load(getActivity(), R.raw.m0001, 1));
        sounddata.put(3, sp.load(getActivity(), R.raw.m0002, 1));
        sounddata.put(4, sp.load(getActivity(), R.raw.m0003, 1));
        sounddata.put(5, sp.load(getActivity(), R.raw.m0004, 1));
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener(){
            @Override
            public void onLoadComplete(SoundPool sound,int sampleId,int status){
                isLoaded=true;
                Toast.makeText(getContext(), "音效加载完成！", Toast.LENGTH_SHORT);
            }
        });
    }

    public void playSound(int sound, int number)
    {
        AudioManager am = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        float audioMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volumnCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        float volumnRatio = volumnCurrent / audioMaxVolumn;
        sp.play(sounddata.get(sound),
                volumnRatio,// 左声道音量
                volumnRatio,// 右声道音量
                1, // 优先级
                 number,// 循环播放次数
                1);// 回放速度，该值在0.5-2.0之间 1为正常速度
    }


    @Override
    public void onDestroy() {
        mAudioProcess.destroy();//-----------------
        Log.d(TAG, "onDestroy");
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    class RecordPlayThread extends Thread {
        public void run() {
            try {
                int bufferSize = mAudioProcess.calculateBufferSize(frequency, 2, 1);//--------
                Log.d(TAG,"RecordPlayThread bufferSize:" + bufferSize);
                byte[] buffer = new byte[bufferSize];
                audioRecord.startRecording();//开始录制
                audioTrack.play();//开始播放
                while (isRecording)
                {
                    //setp 1 从MIC保存数据到缓冲区
                    int bufferReadResult = audioRecord.read(buffer, 0, bufferSize);
                    byte[] tmpBuf_src = new byte[bufferReadResult];
                    System.arraycopy(buffer, 0, tmpBuf_src, 0, bufferReadResult);
                    Log.d(TAG,"-----------------------------------------");
                    Log.d(TAG,"tmpBuf_src size:" + tmpBuf_src.length);
                    //setp 2 进行处理
                    byte[] tmpBuf_processed = new byte[bufferReadResult];
                    if (isProcessing) {
                        //-----------处理------
                        //输入                   输入长度                    输出buf
                        mAudioProcess.processStream10msData(tmpBuf_src, tmpBuf_src.length, tmpBuf_processed);
                        Log.d(TAG,"噪音消除处理tmpBuf_processed size:" + tmpBuf_processed.length);

                    } else {
                        tmpBuf_processed = tmpBuf_src;
                        Log.d(TAG,"噪音不处理tmpBuf_processed size:" + tmpBuf_processed.length);
                    }
                    //写入数据即播放
                    audioTrack.write(tmpBuf_processed, 0, tmpBuf_processed.length);
                    mAudioProcess.AnalyzeReverseStream10msData(tmpBuf_processed, tmpBuf_processed.length);
                }
                Log.d(TAG,"--------isRecording while exit--------");
                audioTrack.stop();
                audioRecord.stop();
            } catch (Throwable t) {
                Log.d(TAG, "", t);
            }
        }
    };



    class JZRecordPlayThread extends Thread {
        public void run() {
            try {
                int bufferSize = mAudioProcess.calculateBufferSize(frequency, 2, 1);//--------
                Log.d(TAG,"JZRecordPlayThread bufferSize:" + bufferSize);
                byte[] buffer = new byte[bufferSize];
                audioTrack.play();//开始播放
                int length=0;
                InputStream is = null;
                while (isRecording)
                {

                    mPlayIndex ++;

                    if(mPlayIndex==1) {
                        is = getActivity().getResources().openRawResource(R.raw.m0000);
                    }
                    else if(mPlayIndex==2) {
                        is = getActivity().getResources().openRawResource(R.raw.m0001);
                    }
                    else if(mPlayIndex==3) {
                        is = getActivity().getResources().openRawResource(R.raw.m0002);
                    }
                    else if(mPlayIndex==4) {
                        is = getActivity().getResources().openRawResource(R.raw.m0003);
                    }
                    else if(mPlayIndex==5) {
                        is = getActivity().getResources().openRawResource(R.raw.m0004);
                    }

                    if(mPlayIndex >=5){
                        mPlayIndex = 0;
                    }

//                   Thread.sleep(1000);
                    is.skip(44);
                    while ((length = is.read(buffer)) > 0)
                    {
                        ByteBuffer bb = ByteBuffer.wrap(buffer);
                        bb.order(ByteOrder.LITTLE_ENDIAN);

                        byte[] tmpBuf_src = new byte[length];
                        System.arraycopy(buffer, 0, tmpBuf_src, 0, length);

                        //setp 2 进行处理
                        byte[] tmpBuf_processed = new byte[length];
                        if (isProcessing)
                        {
                            //输入                   输入长度                    输出buf
                            mAudioProcess.processStream10msData(tmpBuf_src, tmpBuf_src.length, tmpBuf_processed);
                            Log.d(TAG,"噪音消除处理tmpBuf_processed size:" + tmpBuf_processed.length);

                        } else {
                            tmpBuf_processed = tmpBuf_src;
                            Log.d(TAG,"噪音不处理tmpBuf_processed size:" + tmpBuf_processed.length);
                        }
                        //写入数据即播放
                        audioTrack.write(tmpBuf_processed, 0, tmpBuf_processed.length);
                        mAudioProcess.AnalyzeReverseStream10msData(tmpBuf_processed, tmpBuf_processed.length);
                    }
                    Log.d(TAG,"--------while exit--------");
                }
                Log.d(TAG,"--------isRecording while exit--------");
                audioTrack.stop();
            } catch (Throwable t) {
                Log.d(TAG, "", t);
            }
        }
    };



















    class JZRecordPlayThreadPlaySound extends Thread {
        public void run() {
            try {
                while (isRecording)
                {
                    mPlayIndex ++;
                    playSound(mPlayIndex, 0);
                    if(mPlayIndex >=5){
                        mPlayIndex = 0;
                    }
                    Thread.sleep(300);
                }
            } catch (Throwable t) {
                Log.d(TAG, "", t);
            }
        }
    };

}