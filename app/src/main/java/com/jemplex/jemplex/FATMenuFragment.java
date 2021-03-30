package com.jemplex.jemplex;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jemplex.jemplex.response.AppMenu;
import com.jemplex.jemplex.response.MyListener;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class FATMenuFragment extends Fragment implements MyListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    ArrayList<String> childList = new ArrayList<>();
    ArrayList<Integer> childListImages = new ArrayList<Integer>();

    public static String ApplicationId = "FATMOBILEAPP";


    public FATMenuFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FATMenuFragment newInstance(int columnCount) {
        FATMenuFragment fragment = new FATMenuFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fat_menu_list, container, false);

        recyclerView = view.findViewById(R.id.list);
        Bundle bundle = this.getArguments();
        ArrayList<AppMenu> lstMenu = (ArrayList<AppMenu>) bundle.get("valuesArray");
        ArrayList<String> parentList = new ArrayList<>();
        ArrayList<Integer> parentListImages = new ArrayList<Integer>();
        parentListImages.add(R.drawable.ic_envelope);

        childListImages.add(R.drawable.ic_analytics);
        childListImages.add(R.drawable.ic_candy_machine);
        childListImages.add(R.drawable.ic_clipboard);
        childListImages.add(R.drawable.ic_dart_board);
        childListImages.add(R.drawable.ic_tags);

        for (int i = 0; i < lstMenu.size(); i++) {
            if (lstMenu.get(i).parentMenuName == null && lstMenu.get(i).applicationID.equals(ApplicationId)) {
                parentList.add(lstMenu.get(i).menuName);
            } else if (lstMenu.get(i).parentMenuName != null && lstMenu.get(i).applicationID.equals(ApplicationId)) {
                childList.add(lstMenu.get(i).menuName);
                Log.e("###", "childList: " + childList);
            }
        }
        Log.e("###", "parentList: " + parentList);
        callAdapter(parentList, parentListImages, 1);
        return view;
    }

    private void callAdapter(ArrayList<String> parentList, ArrayList<Integer> parentListImages, int value) {
        // Set the adapter
        mColumnCount = 2;
      /*  mColumnCount = 2;
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), mColumnCount));
        }*/
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new FATMenuRecyclerViewAdapter(parentList, this::callbackOnClick, value, parentListImages));

    }

    @Override
    public void callbackOnClick() {
        callAdapter(childList, childListImages, 2);
    }
}