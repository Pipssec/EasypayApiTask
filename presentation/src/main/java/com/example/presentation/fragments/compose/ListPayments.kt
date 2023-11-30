package com.example.presentation.fragments.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.data.util.Lce
import com.example.data.model.paymentsresponse.Payment
import com.example.data.model.paymentsresponse.PaymentsResponse
import com.example.presentation.fragments.SharedViewModel

@Composable
fun PaymentsContent(
    viewModel: SharedViewModel
) {
    val state by viewModel.paymentsState.collectAsState()
    when (state) {
        is Lce.Content<*> -> {
            val items = (state as Lce.Content<PaymentsResponse>).data.response
            LazyColumn()
            {
                items(items) { item ->
                    PaymentItem(item = item)
                }
            }
        }

        is Lce.Error -> {
            Toast.makeText(LocalContext.current, state.toString(), Toast.LENGTH_LONG).show()
        }

        is Lce.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(80.dp),
                    color = Color.Yellow
                )
            }
        }

        else -> {}
    }
}

@Composable
fun PaymentItem(
    modifier: Modifier = Modifier,
    item: Payment
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = " ID: ${item.id.toString()}")
        Text(text = "Title: ${item.title.toString()}")
        if (item.amount?.isNotEmpty() == true) {
            Text(text = "Amount: ${item.amount.toString()}")
        }
        if (item.created != null) {
            Text(text = "Created: ${item.id.toString()}")
        }
    }
}
