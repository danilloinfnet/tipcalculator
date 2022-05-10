package com.example.ads_e2_a2_tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, SeekBar.OnSeekBarChangeListener {

    private lateinit var txtBillTotal : EditText
    private lateinit var txtPeopleQuantity : EditText
    private lateinit var seekBarTip : SeekBar

    private val formatter = NumberFormat.getCurrencyInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtBillTotal = findViewById(R.id.txtBillTotal)
        txtBillTotal.setOnFocusChangeListener(this)

        txtPeopleQuantity = findViewById(R.id.txtPeopleQuantity)
        txtPeopleQuantity.setOnFocusChangeListener(this)

        seekBarTip = findViewById(R.id.seekBarTip)
        seekBarTip.setOnSeekBarChangeListener(this)
    }

    private fun updateBillValue() {
        if (txtBillTotal.text.toString().isNotEmpty() && txtPeopleQuantity.text.toString().isNotEmpty()) {
            var billValue = txtBillTotal.text.toString().toDouble()
            var peopleQuantity = txtPeopleQuantity.text.toString().toInt()

            var lblTipValue = this.findViewById<TextView>(R.id.lblTipValue)
            var tipValue = billValue * seekBarTip.progress / 100
            lblTipValue.setText(formatter.format(tipValue))

            findViewById<TextView>(R.id.lblTotalValue).setText(formatter.format(billValue + tipValue))
            findViewById<TextView>(R.id.lblTotalPerPerson).setText(formatter.format((billValue + tipValue) / peopleQuantity))
        }
    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        this.updateBillValue()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        var lblTipPercentage = findViewById<TextView>(R.id.lblTipPercentage)
        lblTipPercentage.setText("${seekBarTip.progress.toString()}%")

        this.updateBillValue()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }
}