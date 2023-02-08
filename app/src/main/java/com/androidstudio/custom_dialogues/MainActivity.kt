package com.androidstudio.custom_dialogues

import android.app.Activity
import android.app.Dialog
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.Insets.add
import com.androidstudio.custom_dialogues.databinding.ActivityMainBinding
import com.androidstudio.custom_dialogues.databinding.LayoutCustomdialogueBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var i="Name"
    lateinit var arrayAdapter : ArrayAdapter<String>
    var arrayList = arrayListOf<String>()

    override fun onCreate( savedInstanceState: Bundle?) {
        super.onCreate( savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)

        binding.lvListView.adapter= arrayAdapter

        binding.fabBtn.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(resources.getString(R.string.add_data))
            alertDialog.setMessage(resources.getString(R.string.add_data_msg))
            alertDialog.setCancelable(false)

            alertDialog.setNeutralButton(resources.getString(R.string.cancel)){_,_->
                alertDialog.setCancelable(true)


            }






            alertDialog.setPositiveButton(resources.getString(R.string.add)){_,_->
//                i=0
//                arrayList.removeAt(position)
//                arrayAdapter.notifyDataSetChanged()

                arrayList.add((i).toString())
                //to update the list
                arrayAdapter.notifyDataSetChanged()
//                arrayList.add((i).toString())
//                //to update the list
//                arrayAdapter.notifyDataSetChanged()
            }

            alertDialog.show()
//            i++
//            arrayList.add((i).toString())
//            //to update the list
//            arrayAdapter.notifyDataSetChanged()
        }

        binding.lvListView.setOnItemClickListener { _, _, position, _ ->
            var dialog = Dialog(this)
            var dialogBinding = LayoutCustomdialogueBinding.inflate(layoutInflater)

            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT)
            dialogBinding.etName.setText(arrayList[position])

            dialogBinding.btnUpdate.setOnClickListener {
                if(dialogBinding.etName.text.toString().isNullOrEmpty()){
                    dialogBinding.etName.error = resources.getString(R.string.enter_name)
                }
                else
//                    binding.lvListView.setText(dialogBinding.etName.text.toString())
                    arrayList[position]= dialogBinding.etName.text.toString()
                arrayAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            dialogBinding.btnDelete.setOnClickListener {
                arrayList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.show()
        }
    }


}


