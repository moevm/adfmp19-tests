package ru.moevm.examapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CardFlipActivity : FragmentActivity() {
    /**
     * A fragment representing the front of the card.
     */
    class CardFrontFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View = inflater.inflate(R.layout.fragment_card_front, container, false)
    }

    /**
     * A fragment representing the back of the card.
     */
    class CardBackFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View = inflater.inflate(R.layout.fragment_card_back, container, false)
    }

    private var showingBack: Boolean = false

    private fun flipCard() {
        if (showingBack) {
            supportFragmentManager.popBackStack()
            return
        }

        // Flip to the back.

        showingBack = true

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        supportFragmentManager.beginTransaction()

            // Replace the default fragment animations with animator resources
            // representing rotations when switching to the back of the card, as
            // well as animator resources representing rotations when flipping
            // back to the front (e.g. when the system Back button is pressed).
            .setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out
            )

            // Replace any fragments currently in the container view with a
            // fragment representing the next page (indicated by the
            // just-incremented currentPage variable).
            .replace(R.id.container, CardBackFragment())

            // Add this transaction to the back stack, allowing users to press
            // Back to get to the front of the card.
            .addToBackStack(null)

            // Commit the transaction.
            .commit()
    }

}
