import androidx.compose.ui.window.ComposeUIViewController
import com.pascal.imageai.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
