#Kutti Controls

Kutti Controls is a simple android library that provides with a range of ready to use libraries suited for various functionalities.

Currently this supports
   - EditText which has icon over the **left** or **right** ro **both*.

### Version
1.0.0

### Installation

 - Obtaining the code
     - Download the library source code and add it to your project.
     - Or Download the library from <a href="https://github.com/marudhupandiyang/KuttiControls/blob/master/kutticontrols/build/outputs/KuttiControls.jar"> here </a>

###Examples

 Add `xmlns:custom="http://schemas.android.com/apk/res-auto"`to your root layout . Then use the code snippet available below to get the respective functionality.

 - Edit Text with Left Icon
   <br/><img src="http://i.imgur.com/BYn9Jha.png?1" width=500 height=100 /><br/>
 ```xml
     <com.marudhu.kutticontrols.EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter your Phone Number"
        custom:iconLeft="@android:drawable/ic_menu_call"/>
```
If you need the icons to respond to click, you need to set the listeners. Listeners can be set in two different ways
- Via XML code
 ```xml
       <com.marudhu.kutticontrols.EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter your Phone Number"
        custom:iconLeft="@android:drawable/ic_menu_call"
        custom:iconLeftClickAction="leftClickHandler"/>
```
    The in your java code add the function to handle the click.

     ```java
            public void leftClickHandler(){
             // place your code here
            }
     ```

 - Edit Text with Right Icon
 <br/><img src="http://i.imgur.com/LJzbsND.png?1" width=500 height=100 /><br/>
     ```xml
  <com.marudhu.kutticontrols.EditText
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:hint="Enter something to search"
     custom:iconRight="@android:drawable/ic_menu_search/>
```
If you need the icons to respond to click, you need to set the listeners. Listeners can be set in two different ways
- Via XML code
 ```xml
    <com.marudhu.kutticontrols.EditText
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:hint="Enter your Phone Number"
     custom:iconRight="@android:drawable/ic_menu_search
     custom:iconRightClickAction="lftClickHandler"/>
```

        The in your java code add the function to handle the click.

     ```java
        public void rightClickHandler(){
         // place your code here
        }
```
- Edit Text with both Left & Right Icon
   <br/><img src="http://i.imgur.com/dlHIS6x.png?1" width=500 height=100 /><br/>

     ```xml
  <com.marudhu.kutticontrols.EditText
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:hint="Enter something to search"
     custom:iconRight="@android:drawable/ic_menu_search"
     custom:iconLeft="@android:drawable/ic_menu_help" />
    ```
If you need the icons to respond to click, you need to set the listeners. Listeners can be set in two different ways
- Via XML code
 ```xml
    <com.marudhu.kutticontrols.EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter Search Text Here"
        custom:iconLeft="@android:drawable/ic_menu_help"
        custom:iconLeftClickAction="leftClickHandler"
        custom:iconRight="@android:drawable/ic_menu_search"
        custom:iconRightClickAction="rightClickHandler" />
```
        The in your java code add the function to handle the click.

    ```java
        public void rightClickHandler(){
         // place your code here
        }
```

- One can also set Listeners via java code completely.
Set an ID to the control in the XML file.

    ```xml
<com.marudhu.kutticontrols.EditText
    android:id="@+id/myEditText" />
    ```

    Then yourjava code do something like this

    ```java
EditText txt = (EditText) findViewById(R.id.myEditText);
txt.setIconLeftListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //Place your code here
    }
});
```
- Available functions
 - setLeftIcon(Drawable);
  > Set Icon on Left Side of the EditText using this method.

 - setLeftIcon(Drawable,View.OnClickListener);
  > Set Icon on Left Side of the EditText and also a listener for the click on icon using this method.

 - setRightIcon(Drawable);
  > Set Icon on Right Side of the EditText using this method.

 - setRightIcon(Drawable);
  > Set Icon on Right Side of the EditText and also a listener for the click on icon using this method.

 -  setIconLeftListener(View.OnClickListener);
  > Set a click listener for the icon on the left.

 -  setRightIconListener(View.OnClickListener);
 > Set a click listener for the icon on the right.

### Todo's
 - Write Tests
 - Rethink Github Save
 - Add Code Comments
 - Add Night Mode

License
----
GNU

**Free Software, Hell Yeah!**

