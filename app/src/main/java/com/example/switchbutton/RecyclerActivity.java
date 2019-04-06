package com.example.switchbutton;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        SwitchRecyclerAdapter adapter = new SwitchRecyclerAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private class SwitchRecyclerAdapter extends RecyclerView.Adapter<SwitchRecyclerAdapter.SwitchViewHolder> {

        private List<Boolean> mSbStates;


        public SwitchRecyclerAdapter() {
            mSbStates = new ArrayList<>(getItemCount());
            for (int i = 0; i < getItemCount(); i++) {
                mSbStates.add(false);
            }
        }

        @NonNull
        @Override
        public SwitchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_item, parent, false);
            return new SwitchViewHolder(v);
        }


        @Override
        public void onBindViewHolder(@NonNull final SwitchViewHolder holder, final int position) {
            holder.sb.setOnCheckedChangeListener(null);
            holder.sb.setCheckedImmediately(mSbStates.get(position));
            holder.tv.setText("SwitchButton can be used in RecyclerView.");

            holder.sb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                    if (isChecked) {
                        // 模拟网络请求
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                    mSbStates.set(position, isChecked);
                                    Log.e("test", "run: " + "isChecked = " + isChecked);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RecyclerActivity.this, "isChecked = " + isChecked, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                    mSbStates.set(position, isChecked);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RecyclerActivity.this, "isChecked = " + isChecked, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }


                }
            });


        }


        @Override
        public int getItemCount() {
            return 30;
        }

        private class SwitchViewHolder extends RecyclerView.ViewHolder {

            TextView tv;
            SwitchButton sb;

            public SwitchViewHolder(@NonNull View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.recycler_item_tv);
                sb = (SwitchButton) itemView.findViewById(R.id.recycler_item_sb);

            }
        }
    }
}
