package com.example.mfekr.bakiongapp.Fragments;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mfekr.bakiongapp.Model.Step;
import com.example.mfekr.bakiongapp.R;
import com.example.mfekr.bakiongapp.StepsActivity;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class StepDetailsFragment extends Fragment {

    private Step mStep;
    private PlayerView mPlayerView;
    private SimpleExoPlayer mExoPlayer;
    TextView tvDecription;
    ImageButton mButtonNext, mButtonPrev;
    RecipeDetailsFragment.OnStepClickListener mCallback;
    private ArrayList<Step> steps;

    String videoURL = null;
    private int postion;
    long mediaposition = 0;
    boolean playWhenReady;
    int index;
    private long playerStopPosition;


    public StepDetailsFragment() {
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        SharedPreferences sharedPreferences = PreferenceManager
//                .getDefaultSharedPreferences(getContext());
//        if(savedInstanceState != null) {
//            videoURL =savedInstanceState.getString("VIDEO_URL");
//            postion=savedInstanceState.getInt("POSITION");
//            mediaposition=savedInstanceState.getLong("MEDIA_POSITION");
//        }else{
//            videoURL = sharedPreferences.getString("vid", "");
//            postion = sharedPreferences.getInt("position", -1);
//            playWhenReady = true;
//            mediaposition=0;
//        }
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putString("VIDEO_URL", mStep.getVideoURL());
        outState.putInt("POSITION", postion);
        outState.putLong("MEDIA_POSITION", mPlayerView.getPlayer().getCurrentPosition());
        outState.putBoolean("PLAY_WHEN_READY", mPlayerView.getPlayer().getPlayWhenReady());
        outState.putInt("index", index);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle args = this.getArguments();
        if (args != null) {
            mStep = args.getParcelable("step");
        }


        View rootView = inflater.inflate(R.layout.fragment_step_details, container, false);

        if (savedInstanceState != null) {
            releasePlayer();
//            videoURL = savedInstanceState.getString("VIDEO_URL");
            postion = savedInstanceState.getInt("POSITION");
            mediaposition = savedInstanceState.getLong("MEDIA_POSITION");
            playWhenReady = savedInstanceState.getBoolean("PLAY_WHEN_READY");
            index = savedInstanceState.getInt("index");

        } else{
            playWhenReady = true;
        }

        tvDecription = rootView.findViewById(R.id.tv_description);
//        tvDecription.setText(mStep.getDescription());

        mPlayerView = rootView.findViewById(R.id.playerView);
//        initializePlayer(Uri.parse(mStep.getVideoURL()));


        mButtonNext = rootView.findViewById(R.id.btn_next);
        mButtonPrev = rootView.findViewById(R.id.btn_prev);

        setupFragmentUI(mStep);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((StepsActivity) getActivity())
                .setActionBarTitle(mStep.getShortDescription());
    }

//    private void setUpDetailsFragment(){
//        tvDecription.setText(mStep.getDescription());
//    }

    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getContext(), "BakiongApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(playWhenReady);
            mExoPlayer.seekTo(mediaposition);
        }

    }

    private void setupFragmentUI(Step step){
        tvDecription.setText(step.getDescription());
        videoURL = step.getVideoURL();
        if (videoURL.length() > 10)
        {  Log.d("StepFragment","on save after 2 "+mediaposition);
            initializePlayer(Uri.parse(videoURL));
        }
        else
            mPlayerView.setVisibility(View.GONE);

//        initializePlayer(Uri.parse(videoURL));
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            playerStopPosition = mExoPlayer.getCurrentPosition();
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();

    }


}
