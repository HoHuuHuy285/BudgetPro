package com.example.budgetpro.ui.chi;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.budgetpro.R;
import com.example.budgetpro.adapter.ItemClickListener;
import com.example.budgetpro.adapter.LoaiChiRecyclerViewAdapter;
import com.example.budgetpro.adapter.LoaiChiRecyclerViewAdapter;
import com.example.budgetpro.dao.LoaiChiDao;
import com.example.budgetpro.dialog.LoaiChiDetailDialog;
import com.example.budgetpro.dialog.LoaiChiDialog;
import com.example.budgetpro.entity.LoaiChi;

import java.util.List;

public class LoaiChiFragment extends Fragment {
    private RecyclerView mRv;
    private LoaiChiRecyclerViewAdapter mAdapter;
    private LoaiChiViewModel mViewModel;

    public static LoaiChiFragment newInstance() {
        return new LoaiChiFragment();
    }

    public LoaiChiViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new LoaiChiRecyclerViewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final LoaiChiFragment currentFragment = this;

        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = mAdapter.getItem(position);
                LoaiChiDialog dialog = new LoaiChiDialog(getActivity(),currentFragment, loaiChi );
                dialog.show();
            }
        });
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = mAdapter.getItem(position);
                LoaiChiDetailDialog dialog = new LoaiChiDetailDialog(getActivity(), currentFragment,
                        loaiChi);
                dialog.show();
            }
        });
//        Thực hiện xóa khi trượt
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        LoaiChi lc = mAdapter.getItem(position);

                        Toast.makeText(getActivity(), "Loại Chi Đã Được Xóa", Toast.LENGTH_SHORT).show();
                        mViewModel.delete(lc);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaiChiViewModel.class);
        mViewModel.getAllLoaiChi().observe(getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });
    }

}