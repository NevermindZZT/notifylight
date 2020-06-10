package com.letter.notifylight.activity

import android.content.isDarkTheme
import android.content.startActivity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.color.colorChooser
import com.afollestad.materialdialogs.list.listItems
import com.letter.notifylight.LetterApplication
import com.letter.notifylight.R
import com.letter.notifylight.adapter.BindingViewAdapter
import com.letter.notifylight.databinding.ActivityMainBinding
import com.letter.notifylight.viewmodel.MainViewModel
import com.letter.presenter.ItemViewPresenter
import com.letter.presenter.ViewPresenter

class MainActivity : AppCompatActivity(), ViewPresenter, ItemViewPresenter {

    private lateinit var binding: ActivityMainBinding
    private val model by lazy {
        ViewModelProvider
            .AndroidViewModelFactory(LetterApplication.instance())
            .create(MainViewModel::class.java)
    }
    private val adapter by lazy {
        BindingViewAdapter(
            this,
            R.layout.layout_shape_config_item,
            model.components,
            itemViewPresenter = this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!isDarkTheme()) {
            /* Android O以上支持，设置浅色状态栏 */
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        /* 设置Action Bar并使能home按钮 */
        setSupportActionBar(binding.toolbar)

        initBinding()
        initModel()
    }

    override fun onPause() {
        super.onPause()
        model.saveCurrentShape()
    }


    /**
     * 创建菜单
     * @param menu Menu 菜单
     * @return Boolean 是否创建
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_toolbar, menu)
        return true
    }

    /**
     * 菜单选项选择处理
     * @param item 被选中的选项
     * @return Boolean 动作是否被处理
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.select -> {
                val nameList = model.notifyShapes.value?.map {
                    it.name
                }
                MaterialDialog(this).show {
                    listItems(items = nameList) {
                        dialog, index, _ ->
                        model.setCurrentShape(index)
                        dialog.dismiss()
                    }
                    positiveButton(R.string.dialog_positive_button)
                    negativeButton(R.string.dialog_negative_button)
                }
            }
        }
        return true
    }

    private fun initBinding() {
        binding.let {
            it.presenter = this@MainActivity
        }
        binding.componentList.apply {
            this.adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun initModel() {
        model.apply {
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.preview_button -> startActivity(NotifyLightActivity::class.java)
        }
    }

    override fun onItemViewClick(adapter: Any, position: Int, view: View) {
        if (adapter == this.adapter) {
            when (view.id) {
                R.id.color_layout -> {
                    val colors = mutableListOf<Int>()
                    resources.getStringArray(R.array.color_picker_values).forEach {
                        colors.add(Color.parseColor(it))
                    }
                    MaterialDialog(this).show {
                        colorChooser(colors.toIntArray(),
                            initialSelection = model.currentShape.value?.components?.get(position)?.color ?: 0,
                            allowCustomArgb = true,
                            showAlphaSelector = true) {
                                dialog, color ->
                            model.setShapeColor(position, color)
                            this@MainActivity.adapter.notifyItemChanged(position)
                            dialog.dismiss()
                        }
                        positiveButton(R.string.dialog_positive_button)
                        negativeButton(R.string.dialog_negative_button)
                    }
                }
            }
        }
    }
}