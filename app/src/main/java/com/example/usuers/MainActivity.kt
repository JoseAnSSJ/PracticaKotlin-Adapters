package com.example.usuers

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usuers.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAdapter: UsaerAdapter
    private lateinit var linerLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)

        val isFirstTime = preferences.getBoolean(getString(R.string.sharePrefecens), true)
        Log.i("prueba",isFirstTime.toString())

            if(isFirstTime){
                val dialogView = layoutInflater.inflate(R.layout.dialog_registre,null)
                MaterialAlertDialogBuilder(this).setTitle(getString(R.string.title))
                    .setView(dialogView)
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.dialgConfirm)) { _, _ ->
                        val username = dialogView.findViewById<TextInputEditText>(R.id.edUserName).text.toString()
                        with(preferences.edit()){
                            putBoolean(getString(R.string.sharePrefecens), false).apply()
                            putString(getString(R.string.sp_user_name),username).apply()
                        }
                        Toast.makeText(this,getString(R.string.user_regist),Toast.LENGTH_LONG).show()
                    }
                    .setNeutralButton(getString(R.string.invitado),null)
                    .show()

            }else{
                val username = preferences.getString(getString(R.string.sp_user_name), getString(R.string.name_user))
                Toast.makeText(this,"Bienvenido $username",Toast.LENGTH_LONG).show()
            }



        userAdapter = UsaerAdapter(getUsers(),this)

        linerLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linerLayoutManager
            adapter = userAdapter
        }

    }

    private fun getUsers(): MutableList<Usuario>{
        val users = mutableListOf<Usuario>()

        val alain = Usuario(1, "Alain", "Nicolás", "https://frogames.es/wp-content/uploads/2020/09/alain-1.jpg")
        val samanta = Usuario(2, "Samanta", "Meza", "https://upload.wikimedia.org/wikipedia/commons/b/b2/Samanta_villar.jpg")
        val javier = Usuario(3, "Javier", "Gómez", "https://live.staticflickr.com/974/42098804942_b9ce35b1c8_b.jpg")
        val emma = Usuario(4, "Emma", "Cruz", "https://upload.wikimedia.org/wikipedia/commons/d/d9/Emma_Wortelboer_%282018%29.jpg")

        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        return users
    }

    override fun onClick(user: Usuario,position: Int) {

        Toast.makeText(this,"$position ${user.getFullName()}",Toast.LENGTH_LONG).show()

    }
}