# NestableViewPager

## Goal

To fix some problems with options menu in nested viewpager.

 ## Usage

 build.gradle

```gradle
allprojects {
    ...
    repositories {
        ...
        maven {
            url 'https://dl.bintray.com/dsokolov/maven'
        }
    }
}
```

app/build.gradle

```gradle
compile 'me.ilich:NestableViewPager:0.2.0'
```

In your activity:

```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ...
        NestablePagerAdapterHelper.addListenerToPager(pager, this)
        ...
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        NestablePagerAdapterHelper.onCreateOptionsMenu(menu, pager, menuInflater)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var b = NestablePagerAdapterHelper.onOptionsItemSelected(item, pager)
        if(!b){
            b = super.onOptionsItemSelected(item)
        }
        return b
    }
```

Any fragment contains nested ViewPager should implements NestablePagerItem interface:

```kotlin
class PagerFragment : Fragment(), NestablePagerItem {
    ...
    override fun getNestedViewPager(): ViewPager? = pager
    ...
}
```