/*
 * Copyright 2015 Slava Yasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.yasevich.endlessrecyclerview.sample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.yasevich.endlessrecyclerview.EndlessRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Slava Yasevich
 */
public final class MainActivity extends Activity implements EndlessRecyclerView.Pager {

    private static final long DELAY = 1000L;

    private List<Integer> data = generateData();
    private final Adapter adapter = new Adapter(data);
    private final Handler handler = new Handler();

    private EndlessRecyclerView list;

    private boolean loadingBottom = false;
    private boolean loadingTop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (EndlessRecyclerView) findViewById(android.R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setProgressView(R.layout.item_progress);
        list.setAdapter(adapter);
        list.setPager(this);
        list.scrollToPosition(15);
    }

    private List<Integer> generateData() {
        List<Integer> initialData = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            initialData.add(randomInt());
        }
        return initialData;
    }

<<<<<<< HEAD
        findViewById(android.R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLayoutManager(new LinearLayoutManager(v.getContext()));
            }
        });

        findViewById(android.R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLayoutManager(new GridLayoutManager(v.getContext(), 3));
            }
        });

        findViewById(android.R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLayoutManager(new StaggeredGridLayoutManager(
                        3, StaggeredGridLayoutManager.VERTICAL));
            }
        });

        addItems();
=======
    private Integer randomInt() {
        Random rand = new Random();
        return rand.nextInt(1000);
>>>>>>> c47981334de6eece3a031cb5cc6359f47e1f1211
    }

    @Override
    public boolean shouldLoadBottom() {
        return !loadingBottom;
    }

    @Override
    public void loadNextBottomPage() {
        loadingBottom = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.setRefreshingBottom(false);
                loadingBottom = false;
                adapter.addBottom(generateData());
            }
        }, DELAY);
    }

<<<<<<< HEAD
    private void updateLayoutManager(@NonNull RecyclerView.LayoutManager layoutManager) {
        list.setLayoutManager(layoutManager);
        adapter.setCount(0);
        addItems();
    }

    private void addItems() {
        adapter.setCount(adapter.getItemCount() + ITEMS_ON_PAGE);
=======
    @Override
    public boolean shouldLoadTop() {
        return !loadingTop;
    }

    @Override
    public void loadNextTopPage() {
        loadingTop = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.setRefreshingTop(false);
                loadingTop = false;
                adapter.addTop(generateData());
            }
        }, DELAY);
>>>>>>> c47981334de6eece3a031cb5cc6359f47e1f1211
    }

    private static final class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private final List<Integer> data;

        public Adapter(List<Integer> data) {
            this.data = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
<<<<<<< HEAD
            holder.text.setText(String.format("Item: %1$s", position + 1));
=======
            holder.text.setText("Generated item: " + (data.get(position)));
>>>>>>> c47981334de6eece3a031cb5cc6359f47e1f1211
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public void addBottom(List<Integer> newBottomData) {
            int oldSize = data.size();
            data.addAll(newBottomData);
            notifyItemRangeChanged(oldSize, newBottomData.size() - 1);
        }

        public void addTop(List<Integer> newTopData) {
            data.addAll(0, newTopData);
            notifyItemRangeChanged(0, newTopData.size() - 1);
        }
    }

    private static final class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView text;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(android.R.layout.simple_list_item_1, parent, false));
            text = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
