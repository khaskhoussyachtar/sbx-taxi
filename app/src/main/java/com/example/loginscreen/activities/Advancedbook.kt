package com.example.loginscreen.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.widget.*

import androidx.appcompat.app.AppCompatActivity
import com.example.loginscreen.Api.Retrofit
import com.example.loginscreen.QuotesApi
import com.example.loginscreen.R
import com.example.loginscreen.databinding.ActivityMainBinding
import com.example.loginscreen.databinding.AdvancedBookBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.sql.Time
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Advancedbook : AppCompatActivity(),DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
   /* var day=0
    var month=0
    var year=0
    var hour=0
    var minute=0
    private var tab = LongArray(5)*/
    private lateinit var sendBtn: Button
    private lateinit var position_btn: FloatingActionButton
   /* var savedDay=0
    var savedMonth=0
    var savedYear=0
    var savedHour=0
    var savedMinute=0*/
   var   pickDateBtn:Button? = null

    var description: EditText? = null
   private lateinit var binding: AdvancedBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.advanced_book)
        binding = AdvancedBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lateinit var date: Date
        lateinit var time: Time
       /* sendBtn=findViewById(R.id.send_btn)
        position_btn=findViewById(R.id.position_btn)
        myTextView = findViewById(R.id.time_txt)
        description = findViewById(R.id.editText)
       pickDateBtn = findViewById(R.id.pickDateBtn)*/
        pickDate()
        binding.positionBtn.setOnClickListener {  startActivity(Intent(this, AdvancedbookpositionActivity::class.java)) }

       sendBtn.setOnClickListener {
           Log.i("achtar", "date:${binding.dateTxt} ")
            Log.i("achtar", "time:${binding.timeTxt} ")
            if (binding.dateTxt.text.isNullOrBlank()&&binding.timeTxt.text.isNullOrBlank()) {
                Toast.makeText(this, "please fill the required fields", Toast.LENGTH_SHORT)
                    .show()
            }
            else{
                val client = Retrofit.getInstance().create(QuotesApi::class.java)
                val date = binding.dateTxt.text.toString().trim()
                val time = binding.timeTxt.text.toString().trim()
                val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

                GlobalScope.launch() {
                    val user =sharedPreference.getString("user","")?:""
                    val token =sharedPreference.getString("token","")?:""
                    val result=client.addbookAdvance(date + " "+ time,token,false,1,1,user, "")
                    if (result != null){

                        startActivity(Intent(this@Advancedbook, AdvancedbookpositionActivity::class.java))

                    }else{
                        Toast.makeText(applicationContext, "no server reponse", Toast.LENGTH_SHORT).show()

                    }


                }


                // Toast.makeText(this, "${binding.editTextTextEmailAddress.text}is logged in !!", Toast.LENGTH_SHORT).show()//
            }



        }
    }

   /* private fun getDateTimeCalendar()  {
        val cal= Calendar.getInstance()
        day=cal.get(Calendar.DAY_OF_MONTH)
        month=cal.get(Calendar.MONTH)
        year=cal.get(Calendar.YEAR)
        hour=cal.get(Calendar.HOUR)
        minute=cal.get(Calendar.MINUTE)
    }*/
    /*val  pickDateBtn=  findViewById<Button>(R.id.  pickDateBtn)*/

    private fun pickDate(){
        pickDateBtn?.setOnClickListener {
           /* getDateTimeCalendar()*/
            DatePickerDialog(this, this, datetime.get(Calendar.YEAR), datetime.get(Calendar.MONTH), datetime.get(Calendar.DAY_OF_MONTH),).show()
        }
        sendBtn.setOnClickListener {  }
          /*  //difference entre deux day
           // val simpleDateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
           // val startDate = simpleDateFormat.parse("$savedDay/${savedMonth + 1}/$savedYear $savedHour:$savedMinute")
           // val endDate = simpleDateFormat.parse("$day/$month/${year} $hour:$minute:00")
            val diff: Long =startDate.getTime() - endDate.getTime()
            val seconds = diff / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24

            if (tab[0] >= 3600000 && tab[0] <= 604800000) {
            Toast.makeText(this, "The remaining time to process the reservation is ${tab[1]} day(s), ${tab[2]} hour(s), ${tab[3]} minute(s), ${tab[4]} second(s)",
                Toast.LENGTH_SHORT
            ).show()
                sendBtn .isEnabled = true
        } else {
            Toast.makeText(this, "Error, The time is not between 1 hour and 7 days!! ${tab[1]} day(s), ${tab[2]} hour(s), ${tab[3]} minute(s), ${tab[4]} second(s)",
                Toast.LENGTH_SHORT
            ).show()
                sendBtn.isEnabled = false
        }
            val cal= Calendar.getInstance()
            var myTextView: TextView? = null
            // Initialize the variable with a reference to the TextView in your layout
            myTextView = findViewById(R.id.date_txt)
            myTextView.text = SimpleDateFormat("d MMM yyyy").format(cal.time)
        }
    */}


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
       /* savedDay=dayOfMonth
        savedMonth=month
        savedYear= year
        getDateTimeCalendar()*/
        datetime.set(Calendar.YEAR,year)
        datetime.set(Calendar.MONTH,month)
        datetime.set(Calendar.DAY_OF_MONTH,dayOfMonth)
        TimePickerDialog(this,this,datetime.get(Calendar.HOUR_OF_DAY),datetime.get(Calendar.MINUTE),true) .show()

    }
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
    var myTextView: TextView? = null
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        /*savedHour=hourOfDay
        savedMinute=minute
        var myTextView: TextView? = null
        // Initialize the variable with a reference to the TextView in your layout
        myTextView = findViewById(R.id.time_txt)*/

        datetime.set(Calendar.HOUR_OF_DAY,hourOfDay)
        datetime.set(Calendar.MINUTE,minute)
        myTextView?.text   =formatter.format(datetime.time)
     timecomparateur()
    }
    var datetime=Calendar.getInstance()
    private fun timecomparateur(){
        val currentdate=Calendar.getInstance()
        val isdatevalid=datetime.timeInMillis-currentdate.timeInMillis >3600000

            sendBtn.isEnabled=isdatevalid


    }




}
