package com.peter.viewgrouptutorial.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.peter.viewgrouptutorial.R

class CommunicatingFragment : Fragment(R.layout.communicating_fragment) {
    private lateinit var mButton1: Button
    private lateinit var mButton2: Button
    private lateinit var mButton3: Button
    private lateinit var mButton4: Button
    private lateinit var mButton5: Button
    private lateinit var mButton6: Button
    private lateinit var mButton7: Button
    private lateinit var mButton8: Button
    private lateinit var mButton9: Button
    private val mCommunicatingViewModel: CommunicatingViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("communication in fragment onAttach $mCommunicatingViewModel")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("communication in fragment $mCommunicatingViewModel")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mButton1 = view.findViewById(R.id.button1)
        mButton2 = view.findViewById(R.id.button2)
        mButton3 = view.findViewById(R.id.button3)
        mButton4 = view.findViewById(R.id.button4)
        mButton5 = view.findViewById(R.id.button5)
        mButton6 = view.findViewById(R.id.button6)
        mButton7 = view.findViewById(R.id.button7)
        mButton8 = view.findViewById(R.id.button8)
        mButton9 = view.findViewById(R.id.button9)

        mButton1.setOnClickListener {
            mCommunicatingViewModel.selectItem(1)
        }

        mButton2.setOnClickListener {
            mCommunicatingViewModel.selectItem(2)
        }
        mButton3.setOnClickListener {
            mCommunicatingViewModel.selectItem(3)
        }
        mButton4.setOnClickListener {
            mCommunicatingViewModel.selectItem(4)
        }
        mButton5.setOnClickListener {
            mCommunicatingViewModel.selectItem(5)
        }
        mButton6.setOnClickListener {
            mCommunicatingViewModel.selectItem(6)
        }
        mButton7.setOnClickListener {
            mCommunicatingViewModel.selectItem(7)
        }
        mButton8.setOnClickListener {
            mCommunicatingViewModel.selectItem(8)
        }
        mButton9.setOnClickListener {
            mCommunicatingViewModel.selectItem(9)
        }

    }
}