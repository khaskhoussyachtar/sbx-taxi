package com.example.loginscreen.activities

import android.content.Context
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.loginscreen.R

class Driverdialogfragment: DialogFragment() {
    private var content: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = arguments?.getString("content")

        //val style = DialogFragment.STYLE_NO_FRAME
        //val theme = R.style.DialogTheme
        //setStyle(style, theme)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedInstanceState: Bundle
    ): View {
        val view = inflater!!.inflate(R.layout.driver_dialog, container, false)

        val btnAccept = view.findViewById<View>(R.id.buttonAccept) as Button

        val textViewContent = view.findViewById<View>(R.id.textViewContent) as TextView
        textViewContent.text = content;

        btnAccept.setOnClickListener {
            dismiss();
        }
        return view;

    }
    companion object
    {
        fun newInstance(content: String) : Driverdialogfragment
        {
            val f = Driverdialogfragment()

            val args = Bundle()
            args.putString("content", content)
            f.arguments = args

            return f
        }
    }

}
