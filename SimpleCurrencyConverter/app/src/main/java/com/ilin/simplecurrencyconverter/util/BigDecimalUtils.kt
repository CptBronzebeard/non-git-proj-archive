package com.ilin.simplecurrencyconverter.util

import java.math.BigDecimal

fun BigDecimal.bdiv(bd2: BigDecimal) = this.divide(bd2, Constants.MCONTEXT)