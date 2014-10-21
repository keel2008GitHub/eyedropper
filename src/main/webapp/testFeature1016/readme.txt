testFeature (<image_filename> | <path> | <image_list>.txt) [<output>.json]

1.目前仅支持bmp,jpg图像文件处理
2.第一个参数支持三种方式，a.单张图像，b.文件目录（绝对路径），c.文件名或目录列表（绝对路径）
3.第二个参数可选，作为总的结果输出文件名，如果不输入，用当前时间作为输出文件名
4.同时输出与图像文件同名的json结果文件
5.单次处理图像不要超过10万张

例：
testFeature 1.jpg
testFeature d:\tmp
testFeature imgList.txt output.json
