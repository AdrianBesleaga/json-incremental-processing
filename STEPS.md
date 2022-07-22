1. Create models according to .json input (first noticed minor naming issues when having array like - content -> contents, video -> videos)
2. Assuming big file (java -Xmx512m limit)
   1. Possible problem -> OutOfMemoryError exception (insufficient space to allocate an object in the Java heap)
   2. JacksonStreamingApi - https://github.com/FasterXML/jackson-docs/wiki/JacksonStreamingApi
   3. 'Incremental Processing', or 'Token Streams'
   4. https://cassiomolin.com/2019/08/19/combining-jackson-streaming-api-with-objectmapper-for-parsing-json/
   5. Test incremental ingestion data with expected json (file)
3. Getting the playlists
   1. I assume that if the preroll array is empty, we can return the content videos matching the country
   2. Collect all matching playlists
   3. Create a Map (InMemoryDB) where the key is <contentId>_<country> for fast access to playlists
4. Production code
   1. Clean code/design (Interfaces, SOLID, DI)
   2. Tested
   3. Green tests
   4. Code Reviews
5. Improvements
   1. Adding more validations to avoid NPE
   2. Validate mandatory inputs
   3. Break code into smaller functions
   4. More documentation
   5. More unit tests + integration tests