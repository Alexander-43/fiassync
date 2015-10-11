function main()
{
   var connector = remote.connect("fias");
   //var result = connector.post("/javaws/post", jsonUtils.toJSONString({"field1":"value1", "field2":"value2", "field3":"value3"}), "application/json");
var result = connector.post("/javaws/post", jsonUtils.toJSONString(json), "application/json");
//model.res = jsonUtils.toJSONString(eval ("("+result+")"));
model.res = result;
}

main();
