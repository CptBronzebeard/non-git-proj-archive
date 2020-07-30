package com.ilin.simplecurrencyconverter.util

import java.math.MathContext
import java.text.DecimalFormat

class Constants {
    companion object {
        val MCONTEXT = MathContext.DECIMAL128
        val FORMAT = DecimalFormat("#,########.0000")
    }
}