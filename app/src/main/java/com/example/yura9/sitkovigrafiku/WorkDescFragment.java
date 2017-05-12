package com.example.yura9.sitkovigrafiku;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yura9 on 4/9/2017.
 */

public class WorkDescFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Work> mWorks;
    private WorkDescFragment.WorkAdapter mWorkAdapter;
    private WorkList mWorkList;
    private TextView totalDuration;
    private TextView totalEnd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.desc_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.descRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mWorkList = WorkList.get();

        totalDuration = (TextView) view.findViewById(R.id.totalDuration);
        totalEnd = (TextView) view.findViewById(R.id.totalEnd);

        totalDuration.setText(getString(R.string.t_duration, mWorkList.getTotalDaysAmount()));
        totalEnd.setText(getString(R.string.t_end, mWorkList.getDeadLine()));


        updateRV();

        return view;
    }

    private class WorkHolder extends RecyclerView.ViewHolder{
        public TextView dName;
        public TextView dNameFull;
        public TextView dDesc;
        public TextView dStart;
        public TextView dDuration;
        public TextView dEnd;


        public WorkHolder(View v){
            super(v);
            dName = (TextView) itemView.findViewById(R.id.dName);
            dNameFull = (TextView) itemView.findViewById(R.id.dNameFull);
            dDesc = (TextView) itemView.findViewById(R.id.dDesc);
            dStart = (TextView) itemView.findViewById(R.id.dStart);
            dDuration = (TextView) itemView.findViewById(R.id.dDuration);
            dEnd = (TextView) itemView.findViewById(R.id.dEnd);
        }


        private Work mWork;
        public void bindWork(Work work){
            mWork = work;
            dName.setText(getString(R.string.d_work, mWork.getmNumber()));
            dNameFull.setText(mWork.getDescription());
            if (mWork.mRp == 0){
                dDesc.setText(getString(R.string.d_can_not));
            }else {
                dDesc.setText(getString(R.string.d_desc, mWork.mRp));
            }
            dStart.setText(getString(R.string.d_start, mWork.getDateString(mWork.getDate())));
            dDuration.setText(getString(R.string.d_dur, mWork.getTimeCount()));
            dEnd.setText(getString(R.string.d_end, mWork.getDateString(mWork.getEndDate())));
        }
    }

    private class WorkAdapter extends RecyclerView.Adapter<WorkDescFragment.WorkHolder>{

        public WorkAdapter(List<Work> works){
            mWorks = works;
        }

        @Override
        public WorkDescFragment.WorkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            //View view = View.inflate(getActivity(), android.R.layout.simple_list_item_1, parent);//ERROR
            View view = inflater.inflate(R.layout.work_description_item, parent, false);

            return new WorkDescFragment.WorkHolder(view);
        }

        @Override
        public int getItemCount() {
            return mWorks.size();
        }

        @Override
        public void onBindViewHolder(WorkDescFragment.WorkHolder holder, int position) {
            Work work = mWorks.get(position);
            holder.bindWork(work);

        }
    }

    public void updateRV(){
        mWorks = mWorkList.getWorks();
        if (mWorkAdapter == null) {
            mWorkAdapter = new WorkDescFragment.WorkAdapter(mWorks);
            mRecyclerView.setAdapter(mWorkAdapter);
        }
        else {
            mWorkAdapter.notifyDataSetChanged();
        }
    }
}
