package com.example.ieltsscorerecorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IeltsScoreRecorder()
        }
    }
}

@Composable
fun IeltsScoreRecorder() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 入力フィールド
        OutlinedTextField(
            value = text,
            onValueChange = { newValue ->
                text = newValue // 入力値をそのまま許可
            },
            label = { Text("IELTSスコアを入力") },
            modifier = Modifier.fillMaxWidth()
        )

        // エラーメッセージ
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // 記録ボタン
        Button(
            onClick = {
                val input = text.text
                val score = input.toFloatOrNull()

                errorMessage = when {
                    score == null -> "有効な数値を入力してください"
                    score % 0.5f != 0f -> "スコアは0.5で割り切れる必要があります"
                    score >= 9.1f -> "スコアは9.1未満である必要があります"
                    else -> {
                        // 記録処理をここに追加
                        "スコアを記録しました"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("記録")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IeltsScoreRecorderPreview() {
    IeltsScoreRecorder()
}
