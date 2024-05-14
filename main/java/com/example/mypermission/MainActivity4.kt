package com.example.mypermission

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity4 : AppCompatActivity() {

    private lateinit var rvUsageStats: RecyclerView
    private lateinit var usageStatsAdapter: UsageStatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)

        rvUsageStats = findViewById(R.id.rvUsageStats)
        val usageStatsService = UsageStatsService(this)

        val usageStatsMap = usageStatsService.getUsageStatsForSocialMediaApps()
        usageStatsAdapter = UsageStatsAdapter(usageStatsMap)
        rvUsageStats.adapter = usageStatsAdapter
        rvUsageStats.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView.rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

class UsageStatsService(private val context: Context) {

    private val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

    fun getUsageStatsForSocialMediaApps(): Map<String, Long> {
        val cal = Calendar.getInstance()
        val endTime = cal.timeInMillis
        cal.add(Calendar.YEAR, -1)
        val startTime = cal.timeInMillis

        val usageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime)
            .filter { isSocialMediaApp(it.packageName) }
            .groupBy { it.packageName }
            .mapValues { (_, stats) -> stats.sumOf { it.totalTimeInForeground } }

        return usageStats
    }

    private fun isSocialMediaApp(packageName: String): Boolean {
        val socialMediaApps = listOf("com.facebook.katana", "com.twitter.android", "com.instagram.android","com.whatsapp")
        return socialMediaApps.contains(packageName)
    }
}

class UsageStatsAdapter(private val usageStats: Map<String, Long>) :
    RecyclerView.Adapter<UsageStatsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usage_stat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(usageStats.entries.elementAt(position))
    }

    override fun getItemCount() = usageStats.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvAppName: TextView = itemView.findViewById(R.id.tvAppName)
        private val tvUsageTime: TextView = itemView.findViewById(R.id.tvUsageTime)

        fun bind(entry: Map.Entry<String, Long>) {
            tvAppName.text = getAppName(entry.key)
            tvUsageTime.text = formatUsageTime(entry.value)
        }

        private fun getAppName(packageName: String): String {
            val packageManager = itemView.context.packageManager
            return try {
                val appInfo = packageManager.getApplicationInfo(packageName, 0)
                packageManager.getApplicationLabel(appInfo).toString()
            } catch (e: PackageManager.NameNotFoundException) {
                packageName
            }
        }

        private fun formatUsageTime(usageTimeMillis: Long): String {
            val seconds = (usageTimeMillis / 1000) % 60
            val minutes = (usageTimeMillis / (1000 * 60) % 60)
            val hours = (usageTimeMillis / (1000 * 60 * 60) % 24)
            return String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
    }
}