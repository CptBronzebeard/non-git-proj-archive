package com.ilin.simplecurrencyconverter.ui.conversion

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ilin.simplecurrencyconverter.R
import com.ilin.simplecurrencyconverter.database.CurrencyPair
import com.ilin.simplecurrencyconverter.model.Currency
import com.ilin.simplecurrencyconverter.ui.shared.SharedViewModel
import com.ilin.simplecurrencyconverter.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import java.util.*

class ConversionFragment : Fragment() {

    private lateinit var conversionViewModel: ConversionViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var spinner: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var button: Button
    private lateinit var convFrom: EditText
    private lateinit var convResult: EditText
    private lateinit var calendarText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        conversionViewModel =
            ViewModelProviders.of(this).get(ConversionViewModel::class.java)
        conversionViewModel.currencyQueue = conversionViewModel.createQueue(this.context!!)
        val root = inflater.inflate(R.layout.fragment_conversion, container, false)
        spinner = root.findViewById(R.id.spinner2)
        spinner2 = root.findViewById(R.id.spinner3)
        button = root.findViewById(R.id.button)
        convFrom = root.findViewById(R.id.editText)
        calendarText = root.findViewById(R.id.editText2)
        convResult = root.findViewById(R.id.editText3)
        button.setOnClickListener(conversionListener)
        convFrom.addTextChangedListener(textChangeListener)
        spinner.onItemSelectedListener = itemSelectedListener
        spinner2.onItemSelectedListener = itemSelectedListener
        calendarText.inputType = InputType.TYPE_NULL
        calendarText.setOnClickListener(calendarClickListener)
        calendarText.onFocusChangeListener = calendarFocusListener
        conversionViewModel.calendar.observe(this, calendarChangedObserver)
        conversionViewModel.calendar.value = Calendar.getInstance()
        conversionViewModel.currencyList.observe(this, dataReceivedObserver)
        updateHistory()
        return root
    }

    private val dataReceivedObserver = Observer<List<Currency>?> {
        /*val arr = conversionViewModel.getCurrencies()*/
        val adapter =
            it?.let { it1 ->
                ArrayAdapter<Currency>(
                    activity!!.applicationContext, android.R.layout.simple_spinner_dropdown_item,
                    it1
                )
            }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter
        spinner.adapter = adapter
        spinner2.setSelection(conversionViewModel.posTo)
        spinner.setSelection(conversionViewModel.posFrom)
    }
    private val calendarChangedObserver = Observer<Calendar> { newCal ->
        run {
            val fragment = this
            GlobalScope.launch {
                try {
                    conversionViewModel.updateRates(newCal)
                } catch (e: UnknownHostException) {
                    launch(Dispatchers.Main) { exceptionCallback() }
                }
            }
        }
        calendarText.text = conversionViewModel.calendar.value?.toEditable()
    }

    private val conversionListener = View.OnClickListener {
        save()
    }
    private val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val tmpCalendar = Calendar.getInstance()
        tmpCalendar.year = year
        tmpCalendar.month = month
        tmpCalendar.day = day
        conversionViewModel.calendar.value = tmpCalendar
    }
    private val calendarClickListener = View.OnClickListener {
        showDatePickerDialog()
        /*val calendar = conversionViewModel.calendar.value!!
        val day = calendar.day
        val month = calendar.month
        val year = calendar.year
        val dpd = DatePickerDialog(this.context!!, dateListener, year, month, day)
        dpd.datePicker.maxDate = Calendar.getInstance().timeInMillis
        dpd.show()*/
    }
    private val calendarFocusListener = View.OnFocusChangeListener { _: View, b: Boolean ->
        if (b) {
            showDatePickerDialog()
            /*val calendar = conversionViewModel.calendar.value!!
            val day = calendar.day
            val month = calendar.month
            val year = calendar.year
            DatePickerDialog(this.context!!, dateListener, year, month, day).show()*/
        }
    }
    private val itemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            when (p0!!.id) {
                spinner.id -> conversionViewModel.posFrom = p2
                else -> conversionViewModel.posTo = p2
            }
            showConversion()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }
    }
    private val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            showConversion()
        }
    }
    private val exceptionCallback = {
        val text = "Unable to resolve API URL address, please check your internet connection and try again"
        val toast = Toast.makeText(this.context, text, Toast.LENGTH_LONG)
        toast.show()
    }

    private fun showConversion() {
        val strTmp = convFrom.text.toString()
        if (strTmp != "") {
            val tmp = strTmp.toBigDecimal()
            val curFrom = (spinner.selectedItem as Currency)
            val curTo = (spinner2.selectedItem as Currency)
            val pair = CurrencyPair(curFrom, curTo, tmp, conversionViewModel.calendar.value!!)
            val df = Constants.FORMAT

            convResult.text = Editable.Factory.getInstance().newEditable(
                df.format(pair.amountTo)
            )
        }
    }

    private fun save() {
        val strTmp = convFrom.text.toString()
        if (strTmp != "") {
            val tmp = strTmp.toBigDecimal()
            val curFrom = (spinner.selectedItem as Currency)
            val curTo = (spinner2.selectedItem as Currency)
            val pair = CurrencyPair(curFrom, curTo, tmp, conversionViewModel.calendar.value!!)
            val text = "Conversion recorded in history"
            val toast = Toast.makeText(this.context, text, Toast.LENGTH_LONG)
            toast.show()
            GlobalScope.launch {
                updateHistory(conversionViewModel.insertAndGet(pair))
            }

        }
    }

    private fun updateHistory(list: List<CurrencyPair>? = null) {
        GlobalScope.launch(Dispatchers.Main) {
            var tmp = list
            if (tmp == null)
                tmp = conversionViewModel.currencyQueue.getAll()
            sharedViewModel.updateHistory(tmp)
        }
    }

    private fun showDatePickerDialog() {
        val calendar = conversionViewModel.calendar.value!!
        val day = calendar.day
        val month = calendar.month
        val year = calendar.year
        val dpd = DatePickerDialog(this.context!!, dateListener, year, month, day)
        dpd.datePicker.maxDate = Calendar.getInstance().timeInMillis
        dpd.show()
    }

    override fun setUserVisibleHint(visible: Boolean) {
        super.setUserVisibleHint(visible)
        if (visible)
            this.context?.let {
                convFrom.requestFocus()
                val imgr = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imgr.showSoftInput(convFrom, InputMethodManager.SHOW_IMPLICIT)
            }
    }
}

