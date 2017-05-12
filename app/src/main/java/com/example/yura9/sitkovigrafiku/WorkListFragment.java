package com.example.yura9.sitkovigrafiku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yura9 on 1/30/2017.
 */

public class WorkListFragment extends Fragment {
    private static final String WORK_DIALOG = "work_dialog";
    private static final int TARGET_FRAGMENT_REQUEST = 0;
    private RecyclerView mRecyclerView;
    private WorkAdapter mWorkAdapter;
    private List<Work> mWorks;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_container, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.workRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateRV();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateRV();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_work_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addWork:
                Work w = new Work();
                WorkList.get().addWork(w);
                updateRV();
                return true;
            case R.id.calculate:
                Intent intent = new Intent(getActivity(), WorkDescActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private class WorkHolder extends RecyclerView.ViewHolder{
        public EditText mWorkMainParam;
        public EditText mWorkTimeCount;
        public EditText mWorkDescription;
        public EditText mWorkPrev;


        public WorkHolder(View v){
            super(v);
            mWorkMainParam = (EditText) itemView.findViewById(R.id.workMainParam);
            mWorkTimeCount = (EditText) itemView.findViewById(R.id.workTimeCount);
            mWorkDescription = (EditText) itemView.findViewById(R.id.workDescription);
            mWorkPrev = (EditText) itemView.findViewById(R.id.workPrev);


            //itemView.setOnClickListener(this);
        }


        private Work mWork;
        public void bindWork(Work work){
            mWork = work;
            /*mWorkName.setText(mWork.getName());
            mResTextView.setText(getString(R.string.string_time_work, mWork.getResource()));
            mNumberTextView.setText(getString(R.string.string_number_work, mWork.getNumber()));
            mFictiveCheckBox.setChecked(mWork.isFictive());*/

            mWorkMainParam.setText(Integer.toString(mWork.getmNumber()));
            /*mWorkMainParam.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mWork.setmNumber(Integer.parseInt(s.toString()));
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });*/

            mWorkTimeCount.setText(Integer.toString(mWork.getTimeCount()));
            /*mWorkTimeCount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0) {
                        mWork.setTimeCount(Integer.parseInt(s.toString()));
                    }
                    //WorkList.get().updateWork(mWork);
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });*/

            mWorkDescription.setText(mWork.getDescription());
            mWorkPrev.setText(mWork.previous);
        }
    }

    private class WorkAdapter extends RecyclerView.Adapter<WorkHolder>{

        public WorkAdapter(List<Work> works){
            mWorks = works;
        }

        @Override
        public WorkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            //View view = View.inflate(getActivity(), android.R.layout.simple_list_item_1, parent);//ERROR
            View view = inflater.inflate(R.layout.work_list_item2, parent, false);

            return new WorkHolder(view);
        }

        @Override
        public int getItemCount() {
            return mWorks.size();
        }

        @Override
        public void onBindViewHolder(WorkHolder holder, int position) {
            Work work = mWorks.get(position);
            holder.bindWork(work);

        }
    }

    public void updateRV(){
        mWorks = WorkList.get().getWorks();
        if (mWorkAdapter == null) {
            mWorkAdapter = new WorkAdapter(mWorks);
            mRecyclerView.setAdapter(mWorkAdapter);
        }
        else {
            mWorkAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == TARGET_FRAGMENT_REQUEST){
            updateRV();
        }

    }
}
