package com.rickcavallaro.contact;

/*
 * Created by Rick on 2/23/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddressBookFragment extends Fragment {
    private RecyclerView mAddressBookRecyclerView;
    private ContactAdapter mContactAdapter;
    private boolean mShowFavoritesOnly = false;
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onContactSelected(Contact contact);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_address_book, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_book, container,
                false);
        mAddressBookRecyclerView =
                (RecyclerView) view.findViewById(R.id.address_book_recycler_view);
        mAddressBookRecyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        AddressBook addressBook = AddressBook.get(getContext());
        List<Contact> contacts = addressBook.getContacts();
        if (mContactAdapter == null) {
            mContactAdapter = new ContactAdapter(contacts);
            mAddressBookRecyclerView.setAdapter(mContactAdapter);
        }
        else {
            mContactAdapter.setContacts(contacts);
            mContactAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_create_contact:
                Contact contact = new Contact();
                AddressBook.get(getContext()).add(contact);
                mCallbacks.onContactSelected(contact);
                return true;
            case R.id.menu_item_toggle_favorites:
                mShowFavoritesOnly = !mShowFavoritesOnly;
                if (mShowFavoritesOnly) {
                    item.setTitle(R.string.show_all);
                    mContactAdapter.mContacts = AddressBook.get(getContext()).getFavoriteContacts();
                }
                else {
                    item.setTitle(R.string.show_favorites);
                    mContactAdapter.mContacts = AddressBook.get(getContext()).getContacts();
                }
                mContactAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class ContactHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private TextView mContactNameTextView;
        private Contact mContact;

        public ContactHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContactNameTextView = (TextView) itemView;
        }

        public void bindContact(Contact contact) {
            mContact = contact;
            mContactNameTextView.setText(contact.getName());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), mContact.getName() + " clicked.",
                    Toast.LENGTH_SHORT).show();
            mCallbacks.onContactSelected(mContact);
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
        private List<Contact> mContacts;

        public ContactAdapter(List<Contact> contacts) {
            mContacts = contacts;
        }

        @Override
        public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(
                    android.R.layout.simple_list_item_1, parent, false);
            return new ContactHolder(view);
        }

        @Override
        public void onBindViewHolder(ContactHolder holder, int position) {
            Contact contact = mContacts.get(position);
            //holder.mContactNameTextView.setText(contact.getName());
            holder.bindContact(contact);
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
        }

        public void setContacts(List<Contact> contacts) {
            mContacts = contacts;
        }
    }
}
