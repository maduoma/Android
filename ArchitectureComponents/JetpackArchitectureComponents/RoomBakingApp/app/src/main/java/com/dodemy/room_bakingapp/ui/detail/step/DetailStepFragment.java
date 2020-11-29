package com.dodemy.room_bakingapp.ui.detail.step;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.dodemy.room_bakingapp.R;
import com.dodemy.room_bakingapp.data.db.entities.Step;
import com.dodemy.room_bakingapp.utils.Constant;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class DetailStepFragment extends Fragment implements ExoPlayer.EventListener {
    private SimpleExoPlayer exoPlayer;
    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder stateBuilder;
    private Context context;
    private Step step;
    private Boolean isTwoPane;
    private long position;
    private int currentWindow;
    private boolean playWhenReady = true;

    @BindView(R.id.recipe_step_video_view)
    SimpleExoPlayerView exoPlayerView;
    @BindView(R.id.recipe_step_desc)
    TextView descriptionView;
    @BindView(R.id.recipe_step_image)
    ImageView stepThumbnail;
    @BindView(R.id.recipe_step_desc_card)
    CardView descriptionCard;


    public DetailStepFragment() {
        // Required empty public constructor
    }

    public static DetailStepFragment newInstance(Step step) {
        DetailStepFragment stepFragment = new DetailStepFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(Constant.EXTRA_KEY, step);
        stepFragment.setArguments(arguments);
        return stepFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            Timber.d("saveInstance is not null");
            position = savedInstanceState.getLong(Constant.BUNDLE_KEY_CURRENT_POSITION, 0);
            currentWindow = savedInstanceState.getInt(Constant.BUNDLE_KEY_CURRENT_WINDOW, 0);
            playWhenReady = savedInstanceState.getBoolean(Constant.BUNDLE_KEY_PLAY_WHEN_READY, false);
            Timber.d(" "+ playWhenReady);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            step = getArguments().getParcelable(Constant.EXTRA_KEY);
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_step, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        ButterKnife.bind(this, view);

        // check for tablet or phone
        isTwoPane = getResources().getBoolean(R.bool.isTablet);

        setTextAndImageViewValues();
    }

    private void setVideoToExoplayer() {
        int orientation = getResources().getConfiguration().orientation;
        String video = step.getVideoURL();
        if (video != null && !video.isEmpty()) {

            // Init and show video view
            setViewVisibility(exoPlayerView, true);
            initializeMediaSession();
            initializePlayer(Uri.parse(video));

            if (orientation == Configuration.ORIENTATION_LANDSCAPE && !isTwoPane) {
                // Expand video, hide description, hide system UI for phone's landscape
                fullScreenView(exoPlayerView);
                setViewVisibility(descriptionCard, false);
                hideUI();
            }

        } else {
            // Hide video view
            setViewVisibility(exoPlayerView, false);
        }
    }

    private void setTextAndImageViewValues() {
        // set description for step for selected recipe
        descriptionView.setText(step.getDescription());

        // image url for selected step if available
        String imageUrl = step.getThumbnailURL();
        // step image to image view if not null else set is invisible
        if(!TextUtils.isEmpty(imageUrl)) {
            // Load and show Image
            Picasso.with(context)
                    .load(imageUrl)
                    .into(stepThumbnail);
            setViewVisibility(stepThumbnail, true);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void hideUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_LOW_PROFILE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }
    }

    private void fullScreenView(SimpleExoPlayerView exoPlayer) {
        exoPlayer.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        exoPlayer.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
    }

    private void setViewVisibility(View view, boolean show) {
        if (show) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    private void initializePlayer(Uri mediaUri) {
        if (exoPlayer == null) {
            // setting the player
            TrackSelector trackSelector = new DefaultTrackSelector();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(context,
                    trackSelector, new DefaultLoadControl());
            exoPlayer.seekTo(currentWindow, position);
            // setting player to the exo-playerView
            exoPlayerView.setPlayer(exoPlayer);
            // adding listeners, all functions are implemented
            exoPlayer.addListener(this);
            // adding media source to the player
            String userAgent = Util.getUserAgent(getContext(), Constant.USER_AGENT);
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    context, userAgent), new DefaultExtractorsFactory(),
                    null, null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(playWhenReady);
        }
    }

    private void initializeMediaSession() {
        mediaSession = new MediaSessionCompat(context, Constant.MEDIA_SESSION_TAG);

        mediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mediaSession.setMediaButtonReceiver(null);
        stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mediaSession.setPlaybackState(stateBuilder.build());
        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                exoPlayer.setPlayWhenReady(true);
            }

            @Override
            public void onPause() {
                exoPlayer.setPlayWhenReady(false);
            }

            @Override
            public void onSkipToPrevious() {
                exoPlayer.seekTo(0);
            }
        });
        mediaSession.setActive(true);
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            playerState();
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }

        if (mediaSession != null) {
            mediaSession.setActive(false);
        }
    }

    /**
     * Before API Level 24 there is no guarantee of onStop being called.
     * So we have to release the player as early as possible in onPause
     */
    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    /**
     * Starting with API Level 24 (which brought multi and split window mode) onStop is guaranteed
     * to be called and in the paused mode our activity is eventually still visible.
     * Hence we need to wait releasing until onStop.
     */
    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    /**
     * with API level 24 Android supports multiple windows.
     * As our app can be visible but not active in split window mode,
     * we need to initialize the player in onStart.
     */

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            setVideoToExoplayer();
        }
    }

    /**
     * Before API level 24 we wait as long as possible until we grab resources,
     * so we wait until onResume before initializing the player.
     */
    @Override
    public void onResume() {
        super.onResume();

        if ((Util.SDK_INT <= 23 || exoPlayer == null)) {
            setVideoToExoplayer();
        }
    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, exoPlayer.getCurrentPosition(),
                    1f);
        } else if ((playbackState == ExoPlayer.STATE_READY)) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, exoPlayer.getCurrentPosition(),
                    1f);
        }
        mediaSession.setPlaybackState(stateBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(Constant.BUNDLE_KEY_CURRENT_POSITION, position);
        outState.putInt(Constant.BUNDLE_KEY_CURRENT_WINDOW, currentWindow);
        outState.putBoolean(Constant.BUNDLE_KEY_PLAY_WHEN_READY, playWhenReady);
    }

    private void playerState() {
        if (exoPlayer != null) {
            position = exoPlayer.getCurrentPosition();
            currentWindow = exoPlayer.getCurrentWindowIndex();
            playWhenReady = exoPlayer.getPlayWhenReady();
        }
    }
}
