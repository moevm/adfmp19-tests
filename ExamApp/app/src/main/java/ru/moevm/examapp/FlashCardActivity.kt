package ru.moevm.examapp


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.view_activity_flashcards.*
import org.json.JSONObject

class FlashCardActivity(var currentNumber: Int = 1, var flipped: Boolean = false) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_activity_flashcards)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val fileName = "flash_cards.json"
        val json = application.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

        var flipCards = JSONObject(json)
        var JSONArrayOfFlipCards = flipCards.getJSONArray("flashCards")
        val gson = GsonBuilder().setPrettyPrinting().create()

        val numberOfCards = JSONArrayOfFlipCards.length()
        flashCardsCount.setText(numberOfCards.toString())

        var arrayOfFlipCard: Array<FlipCard> = emptyArray<FlipCard>()

        for (i in 0..(numberOfCards - 1)) {
            val item = JSONArrayOfFlipCards.getJSONObject(i).toString()
            var flipCard: FlipCard = gson.fromJson(item, FlipCard::class.java)
            arrayOfFlipCard += flipCard
        }

        flashCardsProgress.min = 0
        flashCardsProgress.max = numberOfCards
        flashCardsProgress.progress = currentNumber

        previousCard.isEnabled = (currentNumber != 1)
        nextCard.isEnabled = (currentNumber != numberOfCards)
        currentFlashCardNumber.setText(currentNumber.toString())
        card_content.text = arrayOfFlipCard.get(currentNumber - 1).question

        card_content.setOnClickListener {
            flipped = !flipped
            var text =
                if (flipped) arrayOfFlipCard.get(currentNumber - 1).answer else arrayOfFlipCard.get(currentNumber).question
            card_content.setText(text)
        }

        nextCard.setOnClickListener {
            currentNumber++
            flipped = false
            card_content.text = arrayOfFlipCard.get(currentNumber - 1).question
            previousCard.isEnabled = (currentNumber != 1)
            nextCard.isEnabled = (currentNumber != numberOfCards)
            currentFlashCardNumber.setText(currentNumber.toString())
            flashCardsProgress.progress = currentNumber
        }

        previousCard.setOnClickListener {
            currentNumber--
            flipped = false
            card_content.text = arrayOfFlipCard.get(currentNumber - 1).question
            previousCard.isEnabled = (currentNumber != 1)
            nextCard.isEnabled = (currentNumber != numberOfCards)
            currentFlashCardNumber.setText(currentNumber.toString())
            flashCardsProgress.progress = currentNumber
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return false
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
