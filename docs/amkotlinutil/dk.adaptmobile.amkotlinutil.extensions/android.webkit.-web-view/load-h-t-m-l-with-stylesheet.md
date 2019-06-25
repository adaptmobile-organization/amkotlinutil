[amkotlinutil](../../index.md) / [dk.adaptmobile.amkotlinutil.extensions](../index.md) / [android.webkit.WebView](index.md) / [loadHTMLWithStylesheet](load-h-t-m-l-with-stylesheet.md)

# loadHTMLWithStylesheet

`fun `[`WebView`](https://developer.android.com/reference/android/webkit/WebView.html)`.loadHTMLWithStylesheet(stylesheetName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, htmlBody: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Loads html into a webview styled by a stylesheet from assets. Example
AMUtil.loadStyledHTML(webview, "style.css", "&amp;lt;p&amp;gt;Example&amp;lt;/p&amp;gt;");

### Parameters

`stylesheetName` - Name of the stylesheet

`htmlBody` - The html string to put in the &amp;lt;body&amp;gt;