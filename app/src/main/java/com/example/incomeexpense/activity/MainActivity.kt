package com.example.incomeexpense.activity

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.incomeexpense.R
import com.example.incomeexpense.adapter.MoreOptionAdapter
import com.example.incomeexpense.database.IncomeExpenseDatabase
import com.example.incomeexpense.databinding.ActivityMainBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.bottomsheet.BottomSheetDialog


class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var RcvList: ListView
    lateinit var mAdView : AdView

    private var mInterstitialAd: InterstitialAd? = null


    var moreImage = arrayOf(
        R.drawable.ic_summary,
        R.drawable.ic_categoryblue,
        R.drawable.ic_categorychart,
        R.drawable.ic_budget,
        R.drawable.ic_paymentwallet,
        R.drawable.ic_chart,
        R.drawable.ic_menubar,
        R.drawable.ic_bluebell,
        R.drawable.ic_accountuser
    )
    var moreImageName = arrayOf(
        "Summary",
        "Category",
        "Category Chart",
        "Budget",
        "Payment Method",
        "Payment Method Chart",
        "Transaction-All Accounts",
        "Reminders", "Account"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        adLoad()
        initView()

    }

    private fun adLoad() {
        //InterstitialAd
        var adRequestIn = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequestIn, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.e(ContentValues.TAG, ""+adError.toString())
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.e(ContentValues.TAG, "Ad was loaded."+interstitialAd.toString())
                mInterstitialAd = interstitialAd
            }
        })
    }


    override fun onResume() {
        super.onResume()
        var inc: Long? = 0
        var exp: Long? = 0
        var dateinc: Long? = 0

        var transactionList = IncomeExpenseDatabase(this).DisplayDatas()
        for (position in 0 until transactionList.size) {
            if (transactionList.get(position).Type == 1) {
                inc = inc?.plus(transactionList.get(position).IncomeExpense)
                binding.txtTotalIncome.setText(inc.toString())
            } else {
                exp = exp?.minus(transactionList.get(position).IncomeExpense)
                binding.txtTotalExpense.setText(exp.toString())
            }
        }
        dateinc = dateinc?.plus((inc!!.plus(exp!!)))
        if (dateinc != null) {
            if (dateinc < 0) {
                binding.txtTotalSaving.setTextColor(Color.RED)
                binding.txtTotalSaving.setText("-$dateinc")
            }
            else{
                binding.txtTotalSaving.setText(dateinc.toString())

            }
        }
    }


    private fun initView() {


        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(ContentValues.TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d(ContentValues.TAG, "Ad dismissed fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                // Called when ad fails to show.
                Log.e(ContentValues.TAG, "Ad failed to show fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(ContentValues.TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(ContentValues.TAG, "Ad showed fullscreen content.")
            }
        }

        val versionName = packageManager.getPackageInfo(packageName, 0).versionName
        val versionCode = packageManager.getPackageInfo(packageName, 0).versionCode
        binding.txtVersion.setText(versionName+versionCode.toString())

        binding.imgMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.LEFT)
        }


        binding.BtnAddCategory.setOnClickListener {
            var intent = Intent(this, AddCategoryActivity::class.java)
            startActivity(intent)
        }
        binding.btnCategory.setOnClickListener {
            var intent = Intent(this, AllCategoryActivity::class.java)
            startActivity(intent)
        }

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->


            // else keep the switch text to enable dark mode
            if (binding.switch1.isChecked) {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//               binding.switch1.text = "Disable dark mode"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
             //   binding.switch1.text = "Enable dark mode"
            }
            // don't try
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        binding.linAllTransaction.setOnClickListener {
            intent = Intent(this,SummaryActivity::class.java)
            startActivity(intent)
        }
        binding.linCategoryChart.setOnClickListener {
            intent = Intent(this,CategoryChartActivity::class.java)
            startActivity(intent)
        }
//        binding.linShareApp.setOnClickListener {
//            intent = Intent(this,ShareAppActivity::class.java)
//        }




        binding.btnMoreOption.setOnClickListener {

            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(R.layout.more_option_view)


            RcvList = bottomSheetDialog.findViewById(R.id.RcvList)!!

            LayoutInflater.from(this).inflate(R.layout.more_option, null)
            var MoreOptionAdapter = MoreOptionAdapter(this, moreImage, moreImageName)
            RcvList.adapter = MoreOptionAdapter
            bottomSheetDialog.show()
        }
        binding.btnRemoveAds.setOnClickListener {
            var intent = Intent(this, RemoveAdsActivity::class.java)
            startActivity(intent)
        }
        binding.btnAddIncome.setOnClickListener {
            var intent = Intent(this, AddIncomeActivity::class.java)
            intent.putExtra("Type", "Income")
            startActivity(intent)
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
                adLoad()
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }

        }
        binding.btnAddExpense.setOnClickListener {
            var intent = Intent(this, AddIncomeActivity::class.java)
            intent.putExtra("Type", "Expense")
            startActivity(intent)
        }
        binding.btnRemainder.setOnClickListener {
            var intent = Intent(this, RemindersActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        var builder = AlertDialog.Builder(this@MainActivity)

        //dialog theme change
//       var builder=AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT)
        builder.setTitle("Confirm Exit!!!")
        builder.setMessage("Are you sure to exit ?")
        builder.setPositiveButton(
            "Yes"
        ) { dialogInterface, i ->
            finish()
        }
        builder.setNegativeButton(
            "No"
        ) { dialogInterface, i ->
            dialogInterface.cancel()
        }
        builder.setCancelable(false)
        builder.show()
    }
}
