# Retrofit-Rx_Project
This app lets you look at the current tech news. You can choose the article you want to read by clicking on it from the list which opens up a browser of the user's choosing


This app makes use of the following:
1. The newsapi.org API: <https://newsapi.org/> This is where we fetch the news data from using a get request
2. Recycler views to display the news items as a list
3. Retrofit: A REST Client to retrieve JSON via REST based service
4. RxJava: The data fetched from JSON is stored as an Observable which is subscribed by the observer
5. OnClick listener on the list together with implicit intent to open the article on the list into a browser
