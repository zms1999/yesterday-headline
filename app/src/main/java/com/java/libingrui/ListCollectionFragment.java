package com.java.libingrui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Locale;

public class ListCollectionFragment extends Fragment {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    CollectionAdapter adapter;
    ViewPager2 viewPager;
    private NewsViewModel mViewModel;
    private ArrayList<String> categoryList; // TODO

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_collection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        initCategory();
        initViewPagerAndTabLayout(view);

//        mViewModel.getNewsList().observe(this, new Observer<NewsList>() {
//            @Override
//            public void onChanged(NewsList newsList) {
//                categoryList = newsList;
//                adapter.setCategoryList(categoryList);
//            }
//        });

        initCategoryButton(view);
    }

    public void initCategory() {
        // TODO
        if (categoryList == null) {
            categoryList = new ArrayList<>();
            categoryList.add("news");
            categoryList.add("paper");
        }
//        adapter.setCategoryList(categoryList);
    }

    public void initViewPagerAndTabLayout(View view) {
        adapter = new CollectionAdapter(this, categoryList);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.OnConfigureTabCallback() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(categoryList.get(position)); // TODO
                    }
                }
        ).attach();
    }

    public void initCategoryButton(View view) {
        Button button = view.findViewById(R.id.button_category);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                TODO
//                Intent intent = new Intent(getContext(), NewsActivity.class);
//                final String CATEGORY = "category";
//                intent.putExtra(CATEGORY, categoryList);
//                startActivityForResult(intent, 23);
                Toast.makeText(getContext(), R.string.test, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 23) {
            // TODO, change the category identifier
        }
    }
}
