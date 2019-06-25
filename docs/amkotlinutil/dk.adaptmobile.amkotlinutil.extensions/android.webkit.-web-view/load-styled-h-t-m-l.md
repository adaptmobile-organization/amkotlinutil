[amkotlinutil](../../index.md) / [dk.adaptmobile.amkotlinutil.extensions](../index.md) / [android.webkit.WebView](index.md) / [loadStyledHTML](load-styled-h-t-m-l.md)

# loadStyledHTML

`fun `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`.loadStyledHTML(style: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, htmlBody: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Loads html into a webview with custom css styling. Example
AMUtil.loadStyledHTML(webview, "{p {text-align: center;color: red;}}", "&amp;lt;p&amp;gt;Example&amp;lt;/p&amp;gt;");

### Parameters

`style` - Css contents

`htmlBody` - The html string to put in the &amp;lt;body&amp;gt;