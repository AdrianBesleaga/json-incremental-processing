1. Create models according to .json input (first noticed minor naming issues when having array like - content -> contents, video -> videos)
2. Assuming big file (java -Xmx512m limit)
   1. Possible problem -> OutOfMemoryError exception (insufficient space to allocate an object in the Java heap)
   2. JacksonStreamingApi - https://github.com/FasterXML/jackson-docs/wiki/JacksonStreamingApi
   3. 'Incremental Processing', or 'Token Streams'
   4. https://cassiomolin.com/2019/08/19/combining-jackson-streaming-api-with-objectmapper-for-parsing-json/
   5. Test incremental ingestion data with expected json (file)
3. 