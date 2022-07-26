package com.developbyte.administrationtask.Interface

import com.developbyte.administrationtask.Abstract.AbstractViewController

interface IMasterViewController {
    fun addFragment(fr: AbstractViewController?)
    fun presetFragment(tag: Int)
    fun presetFragment2(tag: Int): Boolean
    fun presentMenu(tag: Int)
    fun finishThis()
}