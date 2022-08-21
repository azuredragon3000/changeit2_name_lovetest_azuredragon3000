package com.myapp.lovetest_azuredragon3000.cauduyen.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.lovetest_azuredragon3000.SubApp;
import com.myapp.lovetest_azuredragon3000.cauduyen.ActivityCauDuyen;
import com.myapp.lovetest_azuredragon3000.cauduyen.data.ViewModelShareCauDuyen;
import com.myapp.lovetest_azuredragon3000.databinding.FragmentHomeBinding;
import com.myapp.mylibrary.boitinhyeu.FunctionCommon;
import com.myapp.mylibrary.boitinhyeu.ModelDanhNgon;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ViewModelShareCauDuyen model;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        model = new ViewModelProvider(requireActivity()).get(ViewModelShareCauDuyen.class);

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.xinxam.setOnClickListener(v->{
            // move to other fragment
            List<ModelDanhNgon> modelDanhNgonList = ((SubApp) getActivity().getApplication()).getDatabaseNgonTinh().getDanhNgon();
            int random = FunctionCommon.getRandom(modelDanhNgonList.size(),0);
            String s_input = modelDanhNgonList.get(random).getContent();
            ((ActivityCauDuyen)getActivity()).list_item.add((String)s_input);
            binding.ctXinxam.setText((String)s_input);
            ArrayList<String> e = ((ActivityCauDuyen)getActivity()).list_item;
            model.select(e);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}