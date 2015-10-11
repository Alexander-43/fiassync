function main()
{
   var connector = remote.connect("fias");
   var result = connector.post("/javaws/post", jsonUtils.toJSONString({"field1":"value1", "field2":"value2", "field3":"value3"}), "application/json");
model.res = result.toString();
}

main();
