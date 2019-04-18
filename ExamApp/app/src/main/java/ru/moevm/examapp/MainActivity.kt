package ru.moevm.examapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        flashcardTextView.setOnClickListener {
            val intent = Intent(this, FlashCardActivity::class.java)
            startActivity(intent)
        }
        flashcardImageView.setOnClickListener {
            val intent = Intent(this, FlashCardActivity::class.java)
            startActivity(intent)
        }

        practiceTextView.setOnClickListener {
            val intent = Intent(this, PractiseActivity::class.java)
            startActivity(intent)
        }
        practiceImageView.setOnClickListener {
            val intent = Intent(this, PractiseActivity::class.java)
            startActivity(intent)
        }

        testSessionTextView.setOnClickListener {
            val intent = Intent(this, TestSessionActivity::class.java)
            startActivity(intent)
        }
        testSessionImageView.setOnClickListener {
            val intent = Intent(this, TestSessionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
