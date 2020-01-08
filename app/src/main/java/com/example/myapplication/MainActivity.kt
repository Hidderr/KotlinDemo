package com.example.myapplication

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //用于防止艺术模式在播放界面出现
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  t = TestP(3)
        LogUtils.log("测试数据","t.other : ${t.other}")

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


//        TestInstructor(this)
//
//        Socket.setSocketImplFactory(object:SocketImplFactory {
//            override fun createSocketImpl(): SocketImpl {
//                return object : SocketImpl(){
//                    override fun listen(backlog: Int) {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun getOption(optID: Int): Any {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun create(stream: Boolean) {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun setOption(optID: Int, value: Any?) {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun connect(host: String?, port: Int) {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun connect(address: InetAddress?, port: Int) {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun connect(address: SocketAddress?, timeout: Int) {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun bind(host: InetAddress?, port: Int) {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun accept(s: SocketImpl?) {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun getOutputStream(): OutputStream {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun available(): Int {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun sendUrgentData(data: Int) {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun getInputStream(): InputStream {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun close() {
//                        TODO("not implemented") //To animator_play_pause body of created functions use File | Settings | File Templates.
//                    }
//
//
//                }
//
//            }
//        })

    }





    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
