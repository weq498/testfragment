package com.example.weq498.testfragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;

public class fragment1 extends Fragment {
    TextView textView2;
    EditText send_add;
    Button send_btn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // ---Inflate the layout for this fragment---
        View view = inflater.inflate(R.layout.fragment1, container, false);
        textView2 = (TextView) getActivity().findViewById(R.id.textView2);
        send_add = (EditText) view.findViewById(R.id.send_add);
        send_btn = (Button) view.findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendtwo();
                Bundle bundle = getArguments();
                String[] outdata;
                if ((outdata = bundle.getStringArray("data")) != null) {
                    textView2.setText(outdata[0] + send_add.getText().toString());

                } else {
                    Toast.makeText(getActivity(), "data failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }


    public void sendtwo() {
        String aid = "123456789";
        Toast.makeText(getActivity(),String.format("%02X",aid.length()/2), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}