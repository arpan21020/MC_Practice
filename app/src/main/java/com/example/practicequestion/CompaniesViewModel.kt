import android.content.Context
import android.os.Environment
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.io.File

data class MyData(val title: String, val role: String, val datetime: String)

class CompaniesViewModel(private val context: Context) : ViewModel() {
    fun addCompany(data: MyData) {
        val gson = Gson()
        val jsonData = gson.toJson(data)

        // Get the app-specific directory on external storage
        val externalStorageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val sharedDataDir = File(externalStorageDir, "shared_data")

        // Create directories if they don't exist
        if (!sharedDataDir.exists()) {
            sharedDataDir.mkdirs()
        }

        val filePath = File(sharedDataDir, "data.json")
        println("--------------->" + filePath)

        try {
            // Create the file if it doesn't exist
            if (!filePath.exists()) {
                print("filepathExists------------------------")
                filePath.createNewFile()
            }

            // Write data to the file
            filePath.writeText(jsonData)
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle any exceptions that occur during file creation or writing
        }
    }
}