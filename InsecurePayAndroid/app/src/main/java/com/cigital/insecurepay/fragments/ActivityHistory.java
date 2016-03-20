package com.cigital.insecurepay.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cigital.insecurepay.R;
import com.cigital.insecurepay.VOs.TransferFundsVO;
import com.google.gson.Gson;


public class ActivityHistory extends Fragment {

    private Gson gson = new Gson();
    private TextView tvAccountNumber;
    private EditText etAccountNumber;
    private OnFragmentInteractionListener mListener;
    private TransferFundsVO transferfundsVO;

    public ActivityHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View viewObj = inflater.inflate(R.layout.fragment_home, container, false);
        etAccountNumber = (EditText)viewObj.findViewById(R.id.etAccountNumber);
        tvAccountNumber = (TextView)viewObj.findViewById(R.id.tvAccountNumber);
        return inflater.inflate(R.layout.fragment_activity_history, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(TransferFundsVO transferFundsVO) {
        if (mListener != null) {

            mListener.onFragmentInteraction(transferfundsVO);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(TransferFundsVO transferFundsVO);
    }
}
