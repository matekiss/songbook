package cc.mkiss.songbook.ui.songs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cc.mkiss.songbook.R;
import cc.mkiss.songbook.SongbookApplication;
import cc.mkiss.songbook.model.Song;

/**
 * A fragment representing a list of Songs.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnSongListFragmentInteractionListener}
 * interface.
 */
public class SongsFragment
        extends Fragment
        implements SongsScreen, SearchView.OnQueryTextListener {
    @Inject
    SongsPresenter songsPresenter;

    private OnSongListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SongsFragment() {
        SongbookApplication.injector.inject(this);
    }

    public static SongsFragment newInstance() {
        return new SongsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MySongRecyclerViewAdapter(new ArrayList<Song>(), mListener));
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        songsPresenter.searchSong(null);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSongListFragmentInteractionListener) {
            mListener = (OnSongListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        songsPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        songsPresenter.detachScreen();
        mListener = null;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        songsPresenter.searchSong(query);
        return true;
    }

    @Override
    public void showSongs(List<Song> songs) {
        if (recyclerView == null) {
            return;
        }

        recyclerView.swapAdapter(new MySongRecyclerViewAdapter(songs, mListener), false);
    }

    @Override
    public void showSong(Song song) {
        mListener.onSongListFragmentInteraction(song);
    }
}
