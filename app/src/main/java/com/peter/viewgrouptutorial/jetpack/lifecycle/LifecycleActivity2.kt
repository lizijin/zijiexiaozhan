package com.peter.viewgrouptutorial.jetpack.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.OnLifecycleEvent
import com.peter.viewgrouptutorial.R

class LifecycleActivity2 : AppCompatActivity() {
    override fun getLastNonConfigurationInstance(): Any? {
        return super.getLastNonConfigurationInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle2)
        (lifecycle as LifecycleRegistry).handleLifecycleEvent(Lifecycle.Event.ON_CREATE)

        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreateEvent() {
                println("zijieevent onCreateEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStartEvent() {
                println("zijieevent onStartEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResumeEvent() {
                println("zijieevent onResumeEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onPauseEvent() {
                println("zijieevent onPauseEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStopEvent() {
                println("zijieevent onStopEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyEvent() {
                println("zijieevent onDestroyEvent $this")

            }

        })

    }

    override fun onStart() {
        super.onStart()
        (lifecycle as LifecycleRegistry).handleLifecycleEvent(Lifecycle.Event.ON_START)

        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreateEvent() {
                println("zijieevent onCreateEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStartEvent() {
                println("zijieevent onStartEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResumeEvent() {
                println("zijieevent onResumeEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onPauseEvent() {
                println("zijieevent onPauseEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStopEvent() {
                println("zijieevent onStopEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyEvent() {
                println("zijieevent onDestroyEvent $this")

            }

        })
    }

    override fun onResume() {
        super.onResume()
        (lifecycle as LifecycleRegistry).handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreateEvent() {
                println("zijieevent onCreateEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStartEvent() {
                println("zijieevent onStartEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResumeEvent() {
                println("zijieevent onResumeEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onPauseEvent() {
                println("zijieevent onPauseEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStopEvent() {
                println("zijieevent onStopEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyEvent() {
                println("zijieevent onDestroyEvent $this")

            }

        })
    }

    override fun onPause() {
        super.onPause()
        (lifecycle as LifecycleRegistry).handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)

        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreateEvent() {
                println("zijieevent onCreateEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStartEvent() {
                println("zijieevent onStartEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResumeEvent() {
                println("zijieevent onResumeEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onPauseEvent() {
                println("zijieevent onPauseEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStopEvent() {
                println("zijieevent onStopEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyEvent() {
                println("zijieevent onDestroyEvent $this")

            }

        })
    }

    override fun onStop() {
        super.onStop()
        (lifecycle as LifecycleRegistry).handleLifecycleEvent(Lifecycle.Event.ON_STOP)

        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreateEvent() {
                println("zijieevent onCreateEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStartEvent() {
                println("zijieevent onStartEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResumeEvent() {
                println("zijieevent onResumeEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onPauseEvent() {
                println("zijieevent onPauseEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStopEvent() {
                println("zijieevent onStopEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyEvent() {
                println("zijieevent onDestroyEvent $this")

            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        (lifecycle as LifecycleRegistry).handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)

        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreateEvent() {
                println("zijieevent onCreateEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStartEvent() {
                println("zijieevent onStartEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResumeEvent() {
                println("zijieevent onResumeEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onPauseEvent() {
                println("zijieevent onPauseEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStopEvent() {
                println("zijieevent onStopEvent $this")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyEvent() {
                println("zijieevent onDestroyEvent $this")

            }

        })
    }
}