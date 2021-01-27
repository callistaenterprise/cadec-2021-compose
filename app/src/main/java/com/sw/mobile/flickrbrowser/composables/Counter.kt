package com.sw.mobile.flickrbrowser.composables

import androidx.compose.animation.animate
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsProperties.TestTag
import androidx.compose.ui.semantics.accessibilityLabel
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sw.mobile.flickrbrowser.ui.FlickrbrowserTheme

@Composable
fun CountersContainer(modifier: Modifier = Modifier) {
  val (count, setCount) = remember { mutableStateOf(0) }
  Row(
    modifier =
    modifier.background(color = MaterialTheme.colors.surface)
  ) {
    Counter(id = "1", count = count, setCount = setCount)
    Counter(id = "2", count = count, setCount = setCount)
    Counter(id = "3", count = count, setCount = setCount)
  }
}

@Composable
fun CounterContainer(modifier: Modifier = Modifier) {
  val (count, setCount) = remember { mutableStateOf(0) }
  Counter("1", count, setCount)
}

@Composable
fun Counters(modifier: Modifier = Modifier) {
  val (count1, setCount1) = remember { mutableStateOf(0) }
  val (count2, setCount2) = remember { mutableStateOf(0) }
  val (count3, setCount3) = remember { mutableStateOf(0) }
  Row() {
    Counter(id = "1", count = count1, setCount = setCount1)
    Counter(id = "2", count = count2, setCount = setCount2)
    Counter(id = "3", count = count3, setCount = setCount3)
  }
}

@Composable
fun Counter(id: String, count: Int, setCount: (count: Int) -> Unit, modifier: Modifier = Modifier) {
  Row(
    modifier =
    modifier.padding(4.dp).background(color = MaterialTheme.colors.surface).padding(8.dp)

  ) {
    OutlinedButton(
      onClick = { setCount(count + 1) },
      border = BorderStroke(1.dp, MaterialTheme.colors.primary),
      shape = RoundedCornerShape(50), //50% percent,
      modifier = modifier.semantics { testTag = "Counter-$id" }
    ) {
      Text(text = "Count : $count")
    }
  }
}

@Composable
fun CounterWithState(id: String, modifier: Modifier = Modifier) {
  val (count, setCount) = remember { mutableStateOf(0) }
  onCommit(count, {
    if (count > 5) {
      setCount(0)
    }
  })
  OutlinedButton(
    onClick = { setCount(count + 1) },
    border = BorderStroke(1.dp, colors[count]),
    modifier = modifier.semantics { testTag = "Counter-$id" },
    shape = RoundedCornerShape(50)
  ) {
    Text(text = "Count : $count")
  }
}

class CounterState() {
  var count by mutableStateOf(0)

  fun incCount() {
    this.count++
  }

  fun setCountVal(value: Int) {
    this.count = value
  }
}

val AmbientCounter = ambientOf<CounterState>()

@Composable
fun CountersAmbient(modifier: Modifier = Modifier) {
  Providers(AmbientCounter provides CounterState()) {
    val counterState = AmbientCounter.current;
    Column {
      Row() {
        Counter(id = "1", count = counterState.count, setCount = counterState::setCountVal)
        Counter(id = "2", count = counterState.count, setCount = counterState::setCountVal)
        Counter(id = "3", count = counterState.count, setCount = counterState::setCountVal)
      }
      ScaledDot()
    }
  }
}

@Composable
fun ScaledDotRow(modifier: Modifier = Modifier) {
  val counterState = AmbientCounter.current;
  Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
    ScaledDot()
    ScaledDot()
  }
}

@Composable
fun ScaledDot(modifier: Modifier = Modifier) {
  val counterState = AmbientCounter.current;
  val (scale, setScale) = remember { mutableStateOf(0.0f) }
  onCommit(counterState.count, {
    setScale((counterState.count * 0.3).toFloat())
  })
  val animatedScale = animate(scale, TweenSpec(500))
  Box(
    modifier = Modifier.scale(animatedScale).preferredSize(100.dp)
      .clip(CircleShape)
      .background(MaterialTheme.colors.secondary)
  )
}

@Composable
fun CounterButton(id: String, modifier: Modifier = Modifier) {
  val (count, setCount) = remember { mutableStateOf(0) }
  Row(
    modifier = modifier.padding(4.dp)
  ) {
    OutlinedButton(
      onClick = { setCount(count + 1) },
      border = BorderStroke(1.dp, MaterialTheme.colors.primary),
      shape = RoundedCornerShape(50),
      modifier = modifier.semantics { testTag = "Counter-$id" }
    ) {
      Text(text = "Count : $count")
    }
  }
}

var colors = listOf<Color>(Color.Gray, Color.Blue, Color.Green, Color.Black, Color.Red)

@Composable
fun CounterButtonScale(id: String, modifier: Modifier = Modifier) {
  val (count, setCount) = remember { mutableStateOf(0) }
  val (scale, setScale) = remember { mutableStateOf(1.0f) }
  val animatedScale = animate(scale, TweenSpec(300))
  onCommit(count, {
    if (count > 2) {
      setScale(1.0f)
      setCount(0)
    } else if (count > 0) {
      setScale(scale - 0.1f)
    }
  })
  Row(
    modifier = modifier.padding(4.dp)
  ) {
    OutlinedButton(
      onClick = { setCount(count + 1) },
      border = BorderStroke(
        1.dp,
        if (count < 5)
          colors[count]
        else
          MaterialTheme.colors.primary
      ),
      shape = RoundedCornerShape(50),
      modifier = modifier.scale(animatedScale)
    ) {
      Text(text = "Count : $count")
    }
  }
}

@Composable
fun CounterButtons(modifier: Modifier = Modifier) {
  Row(
    modifier = modifier
      .background(
        color = MaterialTheme.colors.surface
      )
  ) {
    CounterButton(id = "1")
    CounterButton(id = "2")
    CounterButton(id = "3")
  }
}

@Composable
fun CountersWithState(modifier: Modifier = Modifier) {
  Row(
    modifier = modifier
      .background(
        color = MaterialTheme.colors.surface
      )
  ) {
    CounterButton(id = "1")
    CounterButton(id = "2")
    CounterButton(id = "3")
  }
}

@Preview
@Composable
fun CounterButtonsPreview() {
  FlickrbrowserTheme {
    CounterButtons()
  }
}

//@Preview
//@Composable
//fun CounterButtonPreview() {
//  CounterButton(id = "1")
//}

@Preview
@Composable
fun CounterButtonScalePreview() {
  Row(
    modifier = Modifier
      .background(
        color = MaterialTheme.colors.surface
      ).padding(16.dp)
  ) {
    CounterButtonScale(id = "1")
  }
}
//@Preview
//@Composable
//fun CountersPreview() {
//  CountersContainer()
//}
//
//@Preview
//@Composable
//fun CountersPreview() {
//  Counters()
//}

