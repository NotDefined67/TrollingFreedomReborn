package com.leomadrassi.trollingfreedomreborn.other

enum class Mode {
    ALPHA, ALPHANUMERIC, NUMERIC, SYMBOLIC, ALPHASYMBOLIC, NUMERICSYMBOLIC, APLHANUMERICSYMBOLIC;

    companion object {
        fun getString(length: Int, mode: Mode): String {
            val s = when (mode) {
                ALPHA -> "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
                ALPHANUMERIC -> "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
                NUMERIC -> "0123456789"
                SYMBOLIC -> "~,.:?;[]{}´`^!@#$%¨&*()-_+=></ "
                ALPHASYMBOLIC -> "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~,.:?;[]{}´`^!@#$%¨&*()-_+=></ "
                NUMERICSYMBOLIC -> "0123456789~,.:?;[]{}´`^!@#$%¨&*()-_+=></ "
                APLHANUMERICSYMBOLIC -> "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~,.:?;[]{}´`^!@#$%¨&*()-_+=></ "
            }
            val builder = StringBuilder()
            for (i in 0 until length) {
                val index = Math.random() * s.length
                builder.append(s[index.toInt()])
            }
            return builder.toString()
        }
    }
}
