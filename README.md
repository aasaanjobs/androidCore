# androidCore
core repository of android

##About Volley Library :
Volley is an HTTP library that makes networking for Android apps easier and faster.
Below are the main advantages of using it:

  -Uses POJOs directly rather than creating JSON obects manually
  
  -Encapsulation of repository layer
  
  -Thread management

####Code Snippet for making network call
###### Create an ExamplePresenterImpl class for making network call
  
> ExampleDO is a class extending BaseDO<T> where T is the type of JSON object     expected as response
  ```sh
 ExampleDO exampleDO = new ExampleDO();
 exampleDO.setGetURL("corresponding_url");
 ```
> Create a CustomServiceImpl object & provide response type & a response &         error listener to its get() method
  
     CustomService service = new CustomServiceImpl(context, exampleDO);
         service.get(JobDetailsDO.class, new CustomRepoListener<ExampleDO>() {
            @Override
            public void onError(Exception error) {
                viewListener.onError(error);
            }
            @Override
            public void onSuccess(JobDetailsDO response) {
                viewListener.onSuccess(response);
            }
        });
        
#####Code Snippet for creating ExamplePresenterImpl object

  > provide the Activity context & Viewlistner object.
  > ViewListener<T> is an interface containing onSuccess(T response) &           onError(Exception error) methods where T is the reponse type expected
  
  ```sh
ExamplePresenter examplePresenter = new ExamplePresenterImpl(context, new ViewListener<ExampleDO>() {
            @Override
            public void onSuccess(ExampleDO response) {
               //override as required
            }

            @Override
            public void onError(Exception error) {
                //override as required
            }
        });
        

```
  

