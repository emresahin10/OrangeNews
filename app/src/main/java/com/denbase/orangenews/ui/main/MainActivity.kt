package com.denbase.orangenews.ui.main

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.denbase.orangenews.R
import com.denbase.orangenews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import github.com.st235.lib_expandablebottombar.ExpandableBottomBar
import github.com.st235.lib_expandablebottombar.MenuItemDescriptor
import github.com.st235.lib_expandablebottombar.navigation.ExpandableBottomBarNavigationUI


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val bottomBar: ExpandableBottomBar = binding.expandableBottomBar
        val menu = bottomBar.menu
        val navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.menuItemHome, R.id.menuItemCategory, R.id.menuItemSave))
        setupActionBarWithNavController(navController, appBarConfiguration)
        ExpandableBottomBarNavigationUI.setupWithNavController(bottomBar, navController)

        menu.add(
            MenuItemDescriptor.Builder(
                this,
                R.id.menuItemHome,
                R.drawable.ic_home,
                R.string.home,
                Color.WHITE
            )
                .build()
        )
        menu.add(
            MenuItemDescriptor.Builder(
                this,
                R.id.menuItemCategory,
                R.drawable.ic_category,
                R.string.category,
                Color.WHITE
            )
                .build()
        )
        menu.add(
            MenuItemDescriptor.Builder(
                this,
                R.id.menuItemSave,
                R.drawable.ic_bookmark,
                R.string.save,
                Color.WHITE
            )
                .build()
        )


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

}