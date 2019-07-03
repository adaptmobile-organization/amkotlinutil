[amkotlinutil](../../index.md) / [dk.adaptmobile.amkotlinutil.extensions](../index.md) / [android.webkit.WebView](./index.md)

### Extensions for android.webkit.WebView

| [loadHTMLWithStylesheet](load-h-t-m-l-with-stylesheet.md) | `fun `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`.loadHTMLWithStylesheet(stylesheetName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, htmlBody: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Loads html into a webview styled by a stylesheet from assets. Example AMUtil.loadStyledHTML(webview, "style.css", "&amp;lt;p&amp;gt;Example&amp;lt;/p&amp;gt;"); |
| [loadStyledHTML](load-styled-h-t-m-l.md) | `fun `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`.loadStyledHTML(style: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, htmlBody: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Loads html into a webview with custom css styling. Example AMUtil.loadStyledHTML(webview, "{p {text-align: center;color: red;}}", "&amp;lt;p&amp;gt;Example&amp;lt;/p&amp;gt;"); |

