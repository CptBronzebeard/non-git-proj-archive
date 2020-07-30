package com.ilin.simplecurrencyconverter.ui.history

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ilin.simplecurrencyconverter.R
import com.ilin.simplecurrencyconverter.database.CurrencyPair
import com.ilin.simplecurrencyconverter.ui.shared.SharedViewModel
import com.ilin.simplecurrencyconverter.util.Constants
import com.ilin.simplecurrencyconverter.util.toFormattedString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private val sharedModel: SharedViewModel by activityViewModels()
    private lateinit var linearLayout: LinearLayout
    private lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel =
            ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_history, container, false)
        this.root = root
        //queue = QueueAdapter.getInstance(this.context!!)
        linearLayout = root.findViewById(R.id.history)
        sharedModel.conversionHistory.observe(this, historyObserver)
        return root
    }

    /*override fun onStart() {
        super.onStart()
        updateHistory(linearLayout)
    }*/

    /*override fun onResume() {
        super.onResume()
        updateHistory(linearLayout)
    }*/
    val historyObserver = Observer<List<CurrencyPair>> { newList ->
        val context = this.context!!
        linearLayout.removeAllViews()
        GlobalScope.launch(Dispatchers.Main) {
            val inflater =
                ContextWrapper(context).baseContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            newList.forEach {
                val view = inflater.inflate(R.layout.history_entry, linearLayout, false)
                val shortFrom = view.findViewById(R.id.tv1_1) as TextView
                val nameFrom = view.findViewById(R.id.tv2_1) as TextView
                val amountFrom = view.findViewById(R.id.tv3_1) as TextView
                val shortTo = view.findViewById(R.id.tv1_2) as TextView
                val nameTo = view.findViewById(R.id.tv2_2) as TextView
                val amountTo = view.findViewById(R.id.tv3_2) as TextView
                val date = view.findViewById(R.id.dateTv) as TextView
                val df = Constants.FORMAT
                shortFrom.text = it.charCodeFrom
                nameFrom.text = it.nameFrom
                amountFrom.text = df.format(it.amountFrom)
                shortTo.text = it.charCodeTo
                nameTo.text = it.nameTo
                amountTo.text = df.format(it.amountTo)
                date.text = it.date.toFormattedString()
                linearLayout.addView(view)
            }
        }
    }

    override fun setUserVisibleHint(visible: Boolean) {
        super.setUserVisibleHint(visible)
        this.context?.let {
            if (visible) {
                removePhoneKeypad()
            }
        }
        //this.activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

    }

    fun removePhoneKeypad() {
        val inputManager: InputMethodManager = root
            .context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val binder = view!!.windowToken
        inputManager.hideSoftInputFromWindow(
            binder,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}