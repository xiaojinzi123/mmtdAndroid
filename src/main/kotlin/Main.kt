import java.io.File
import java.io.InputStreamReader

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        throw IllegalArgumentException("args is empty!!!")
    }
    val fileName = args.first()
    // 获取命令调用的目录
    val targetFolderPath = InputStreamReader(Runtime.getRuntime().exec("pwd").inputStream).readText().trim()
    // val targetFolderPath = "/Users/hhkj/Downloads/编组 3_slices"
    val folderFile = File(targetFolderPath)
    // 文件夹重命名
    folderFile
        .listFiles()
        // 过滤出文件夹
        ?.filter { it.isDirectory && it.name.contains(other = "mipmap") }
        ?.forEach { itemFolder ->
            val afterNameReplace = itemFolder.name.replace(oldValue = "mipmap", newValue = "drawable")
            val renameResult = itemFolder.renameTo(File(itemFolder.parentFile, afterNameReplace))
            println("itemFolder.name = ${itemFolder.name}, afterNameReplace = $afterNameReplace, renameResult = $renameResult")
        }
    // 文件重命名
    folderFile
        .listFiles()
        ?.filter { it.isDirectory && it.name.contains(other = "drawable") }
        ?.mapNotNull { folderItem ->
            folderItem.listFiles()?.firstOrNull { it.isFile }
        }
        ?.forEach { itemFile ->
            itemFile.renameTo(
                File(
                    itemFile.parentFile,
                    "$fileName.${itemFile.extension}"
                )
            )
        }
    println("targetFolderPath = $targetFolderPath, \nargs = ${args.joinToString()}")
}