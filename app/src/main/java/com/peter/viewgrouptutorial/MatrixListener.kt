package com.peter.viewgrouptutorial

import android.content.Context
import com.tencent.matrix.plugin.DefaultPluginListener
import com.tencent.matrix.report.Issue
import com.tencent.matrix.util.MatrixLog

class MatrixListener(context: Context?) : DefaultPluginListener(context) {
    companion object {
        const val TAG: String = "Matrix.TestPluginListener"
    }

    override fun onReportIssue(issue: Issue) {
        super.onReportIssue(issue)
        MatrixLog.e(TAG, issue.toString())

    }
}